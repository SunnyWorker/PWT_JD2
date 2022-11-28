package org.modsen.eventworker.dao.hibernate;

import org.modsen.eventworker.dao.pojo.Event;

import java.util.List;

public interface EventDAO {
    Event findEventById(long id);
    List<Event> findAllEvents();
    void saveEvent(Event event);
    void removeEvent(Event event);
}
