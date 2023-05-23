package com.example.brainstorm.courses.announcements;

import com.example.brainstorm.courses.Course;
import com.example.brainstorm.courses.Searchable;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "announcement")
public class Announcement implements Searchable {
    @Id
    @SequenceGenerator(
            name = "announcement_sequence",
            sequenceName = "announcement_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "announcement_sequence"
    )
    @Column(name = "announcement_id")
    private Long announcement_id;
    @JsonBackReference(value = "course-anmt")
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDateTime announcementDate;

    public Announcement(Long announcement_id, Course course, String title, String description, LocalDateTime announcementDate) {
        this.announcement_id = announcement_id;
        this.course = course;
        this.title = title;
        this.description = description;
        this.announcementDate = announcementDate;
    }

    public Announcement(Course course, String title, String description, LocalDateTime announcementDate) {
        this.course = course;
        this.title = title;
        this.description = description;
        this.announcementDate = announcementDate;
    }

    public Announcement(String title, String description, LocalDateTime announcementDate) {
        this.title = title;
        this.description = description;
        this.announcementDate = announcementDate;
    }

    public Announcement() {
        super();
    }
    public Long getAnnouncement_id() {
        return announcement_id;
    }

    public void setAnnouncement_id(Long announcement_id) {
        this.announcement_id = announcement_id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    public LocalDateTime getAnnouncementDate() {
        return announcementDate;
    }

    public void setAnnouncementDate(LocalDateTime announcementDate) {
        this.announcementDate = announcementDate;
    }

    @Override
    public boolean contains(String searchedString) {
        return title.toLowerCase().contains(searchedString.toLowerCase()) || description.toLowerCase().contains(searchedString.toLowerCase());
    }
}
