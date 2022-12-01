package org.modsen.eventworker.services.implementation;

import org.modsen.eventworker.dao.hibernate.EventDAO;
import org.modsen.eventworker.dao.pojo.Event;
import org.modsen.eventworker.services.sorting.SortingParameter;
import org.modsen.eventworker.services.sorting.enums.SortingMethod;
import org.modsen.eventworker.exceptions.DuplicateEntityException;
import org.modsen.eventworker.exceptions.NoSuchEntityFoundException;
import org.modsen.eventworker.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EventServiceImplementation implements EventService {

    private EventDAO eventDAO;

    @Autowired
    public EventServiceImplementation(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }


    @Override
    public List<Event> findAllEvents(SortingMethod[] sortingMethods)
    {
        List<SortingParameter> sortingParameterList = Arrays.stream(sortingMethods)
                .map(sortingMethod -> new SortingParameter(sortingMethod))
                .toList();

        addCustomSort(sortingParameterList);

        List<SortingParameter> actualSortingParameters = sortingParameterList.stream()
                .filter(sortingParameter -> sortingParameter!=null)
                .toList();

        return eventDAO.findAllEvents(actualSortingParameters);
    }

    protected void addCustomSort(List<SortingParameter> sortingParameterList) {
        addSortFields(sortingParameterList.get(0),"theme");
        addJoinEntityNames(sortingParameterList.get(1),"organizer");
        addSortFields(sortingParameterList.get(1),"lastName","firstName");
        addSortFields(sortingParameterList.get(2),"time");
    }

    protected void addSortFields(SortingParameter sortingParameter, String...sortFields) {
        if(sortingParameter==null) return;
        sortingParameter.setSortFields(sortFields);
    }

    protected void addJoinEntityNames(SortingParameter sortingParameter, String...joinEntityNames) {
        if(sortingParameter==null) return;
        sortingParameter.setJoinEntityNames(joinEntityNames);
    }

    @Override
    public Event findEventById(long id) {
        return eventDAO.findEventById(id).orElseThrow(()->new NoSuchEntityFoundException("Event with id "+id+" not found!"));
    }

    @Override
    public void registerEvent(Event event) {
        if (event.getId()!=null && eventDAO.findEventById(event.getId()).isPresent())
            throw new DuplicateEntityException("Event with id "+event.getId()+"already exists!");
        else eventDAO.saveEvent(event);
    }

    @Override
    public void changeEvent(Event event) {
        if(event.getId()!=null && findEventById(event.getId())!=null)
            eventDAO.saveEvent(event);
    }

    @Override
    public void deleteEvent(Event event) {
        eventDAO.removeEvent(event);
    }
}
