package org.modsen.common.dao.hibernate;

import org.modsen.common.dao.pojo.Event;
import org.modsen.common.services.sorting.SortingParameter;

import java.util.List;
import java.util.Optional;

public interface EventDAO {
    Optional<Event> findEventById(long id);
    List<Event> findAllEvents(List<SortingParameter> sortingParameters);
    void saveEvent(Event event);
    void removeEvent(Event event);
}
