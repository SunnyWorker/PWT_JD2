package org.modsen.common.services;

import org.modsen.common.dao.pojo.Event;
import org.modsen.common.services.sorting.enums.SortingMethod;

import java.util.List;

public interface EventService {
    List<Event> findAllEvents(SortingMethod... sortingParameters);
    Event findEventById(long id);
    void registerEvent(Event event);
    void changeEvent(Event event);
    void deleteEvent(Event event);
}
