package org.modsen.eventworker.controllers;

import org.modsen.eventworker.dao.pojo.Event;
import org.modsen.eventworker.dao.pojo.Place;
import org.modsen.eventworker.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/event")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/getAll")
    public List<Event> findAllEvents() {
        return eventService.findAllEvents();
    }

    @GetMapping("/get")
    public Event findEventById(@RequestParam long id) {
        return eventService.findEventById(id);
    }

    @PutMapping("/add")
    public void registerEvent(Event event) {
        eventService.registerEvent(event);
    }

    @PatchMapping("/change")
    public void changeEvent(Event event) {
        eventService.changeEvent(event);
    }

    @DeleteMapping("/delete")
    public void deleteEvent(Event event) {
        eventService.deleteEvent(event);
    }

}
