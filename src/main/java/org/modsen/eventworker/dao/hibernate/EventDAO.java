package org.modsen.eventworker.dao.hibernate;

import org.modsen.eventworker.dao.pojo.Event;
import org.modsen.eventworker.services.sorting.SortingParameter;
import org.modsen.eventworker.services.sorting.enums.SortingMethod;

import java.util.List;
import java.util.Optional;

public interface EventDAO {
    Optional<Event> findEventById(long id);
    List<Event> findAllEvents(List<SortingParameter> sortingParameters);
    void saveEvent(Event event);
    void removeEvent(Event event);
}
