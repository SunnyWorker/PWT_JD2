package org.modsen.eventworker.services.implementation;

import org.modsen.eventworker.dao.hibernate.EventDAO;
import org.modsen.eventworker.dao.pojo.Event;
import org.modsen.eventworker.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EventServiceImplementation implements EventService {

    private EventDAO eventDAO;

    @Autowired
    public EventServiceImplementation(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Override
    public List<Event> findAllEvents() {
        return null;
    }

    @Override
    public Event findEventById(long id) {
        return null;
    }

    @Override
    public void registerEvent(Event event) {

    }

    @Override
    public void changeEvent(Event event) {

    }

    @Override
    public void deleteEvent(Event event) {

    }
}
