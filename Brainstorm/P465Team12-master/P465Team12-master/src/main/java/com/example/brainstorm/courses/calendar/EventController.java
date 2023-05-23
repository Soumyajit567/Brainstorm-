package com.example.brainstorm.courses.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("calendar")
public class EventController {

    @Autowired
    private EventService eventService;
    @GetMapping
    public List<Event> events() {
        return eventService.getAll();
    }

    @GetMapping("/{course_id}")
    public List<Event> getCourseEvents(@PathVariable Long course_id) {
        return eventService.getCourseEvents(course_id);
    }


}
