package com.example.brainstorm.courses.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public List<Event> getCourseEvents(Long course_id) { return eventRepository.findAll(); }
}
