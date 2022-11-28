package org.modsen.eventworker.controllers;

import org.modsen.eventworker.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/event")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/getAll")
    public List<Event> findAllEvents() {

    }

    @GetMapping("/get")
    public Event findEventById(@RequestParam long id) {

    }

    @PutMapping("/add")
    public void registerEvent(Event event) {

    }

    @PatchMapping("/change")
    public void changeEvent(Event event) {

    }

    @DeleteMapping("/delete")
    public void deleteEvent(Event event) {

    }

}
