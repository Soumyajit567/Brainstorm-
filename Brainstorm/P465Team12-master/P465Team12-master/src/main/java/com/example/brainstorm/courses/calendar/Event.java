package com.example.brainstorm.courses.calendar;

import com.example.brainstorm.courses.Course;
import com.example.brainstorm.courses.Searchable;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="events")
public class Event implements Searchable {
    @Id
    @SequenceGenerator(
            name = "event_sequence",
            sequenceName = "event_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_sequence"
    )
    private Long event_id;
    @Column(nullable = false)
    private String title;
    private String description;
    @Column(nullable = false )
    private LocalDateTime start;
    @Column(nullable = false)
    private LocalDateTime finish; //note: SQL does not like calling columns "end"
    @Column(name="start_date")
    private Date startDate;
    @Column(name="end_date")
    private Date endDate;
    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference(value = "course-events")
    private Course course;

    /*
        note: include something like "role" for Users to denote
            - exams
            - quizzes
            - assignments
            
     */

    public Event(Long event_id, String title, String description, LocalDateTime start, LocalDateTime finish) {
        this.event_id = event_id;
        this.title = title;
        this.description = description;
        this.start = start;
        this.finish = finish;

    }

    public Event(Long event_id, String title, String description, LocalDateTime start, LocalDateTime finish, Course course) {
        this.event_id = event_id;
        this.title = title;
        this.description = description;
        this.start = start;
        this.finish = finish;
        this.course = course;
    }

    public Event(Course course, String title, String description, LocalDateTime start, LocalDateTime finish) {
        this.title = title;
        this.description = description;
        this.start = start;
        this.finish = finish;
        this.course = course;
    }

    public Event(String title, String description, LocalDateTime start, LocalDateTime finish) {
        this.title = title;
        this.description = description;
        this.start = start;
        this.finish = finish;
    }

    public Event() {
        super();
    }

    public Long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Long id) {
        this.event_id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    public void setFinish(LocalDateTime finish) {
        this.finish = finish;
    }

    @Override
    public boolean contains(String searchedString) {
        return title.toLowerCase().contains(searchedString.toLowerCase()) || description.toLowerCase().contains(searchedString.toLowerCase());
    }
}