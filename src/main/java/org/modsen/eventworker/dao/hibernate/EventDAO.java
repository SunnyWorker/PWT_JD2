package org.modsen.eventworker.dao.hibernate;

import org.modsen.eventworker.dao.pojo.Event;

import java.util.List;
import java.util.Optional;

public interface EventDAO {
    Optional<Event> findEventById(long id);
    List<Event> findAllEvents();
    void saveEvent(Event event);
    void removeEvent(Event event);
}
