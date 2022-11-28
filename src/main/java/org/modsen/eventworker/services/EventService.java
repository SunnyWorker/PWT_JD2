package org.modsen.eventworker.services;

import org.modsen.eventworker.dao.pojo.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {
    List<Event> findAllEvents();
    Event findEventById(long id);
    void registerEvent(Event event);
    void changeEvent(Event event);
    void deleteEvent(Event event);
}
