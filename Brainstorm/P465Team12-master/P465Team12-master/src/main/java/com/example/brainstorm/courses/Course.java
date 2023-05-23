package com.example.brainstorm.courses;

import com.example.brainstorm.courses.announcements.Announcement;
import com.example.brainstorm.courses.assignments.Assignment;
import com.example.brainstorm.courses.calendar.Event;
import com.example.brainstorm.courses.messages.Message;
import com.example.brainstorm.user.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.heroku.api.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.example.brainstorm.user.Users;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    @Column(name = "course_id", nullable = false)
    private Long course_id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description")
    private String description;
    @JsonManagedReference(value = "course-events")
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Event> events = new ArrayList<>();
    @JsonManagedReference(value = "course-anmt")
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Announcement> announcements = new ArrayList<>();
    @JsonManagedReference(value = "course-asgmt")
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Assignment> assignments = new ArrayList<>();
    @JsonManagedReference(value = "course-msg")
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> messages = new LinkedList<>();
    @JsonBackReference(value = "prof-classes")
    @ManyToOne
    @JoinColumn(name = "prof_id")
    private Users prof;



    public Course(String title, String description, List<Event> events, List<Announcement> announcements, List<Assignment> assignments, List<Message> messages) {
        this.title = title;
        this.description = description;
        this.events = events;
        this.announcements = announcements;
        this.assignments = assignments;
        this.messages = messages;
    }

    public Course(Long course_id, String title, String description, List<Event> events, List<Announcement> announcements) {
        this.course_id = course_id;
        this.title = title;
        this.description = description;
        this.events = events;
        this.announcements = announcements;
    }

    public Course(String title, String description, List<Event> events, List<Announcement> announcements) {
        this.title = title;
        this.description = description;
        this.events = events;
        this.announcements = announcements;
    }

    public Course() {
        super();
    }
    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
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

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Users getProf() {
        return prof;
    }

    public void setProf(Users prof) {
        this.prof = prof;
    }



    @Override
    public String toString() {
        return "Course{" +
                "course_id=" + course_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", events=" + events +
                ", announcements=" + announcements +
                ", assignments=" + assignments +
                ", messages=" + messages +
                '}';
    }
}
