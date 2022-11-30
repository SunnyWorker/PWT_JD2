package org.modsen.eventworker.controllers;

import org.modsen.eventworker.dao.pojo.Event;
import org.modsen.eventworker.enums.SortingParameter;
import org.modsen.eventworker.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/getAll")
    public List<Event> findAllEvents(@RequestParam(required = false) SortingParameter sortedByTheme,
                                     @RequestParam(required = false) SortingParameter sortedByOrganizer,
                                     @RequestParam(required = false) SortingParameter sortedByTime)
    {
        return eventService.findAllEvents(
                sortedByTheme,
                sortedByOrganizer,
                sortedByTime
        );
    }

    @GetMapping("/get")
    public Event findEventById(@RequestParam long id) {
        return eventService.findEventById(id);
    }

    @PostMapping("/add")
    public void registerEvent(@RequestBody Event event) {
        eventService.registerEvent(event);
    }

    @PatchMapping("/change")
    public void changeEvent(@RequestBody Event event) {
        eventService.changeEvent(event);
    }

    @DeleteMapping("/delete")
    public void deleteEvent(@RequestBody Event event) {
        eventService.deleteEvent(event);
    }

}
