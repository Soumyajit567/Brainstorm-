package com.example.brainstorm.courses.messages;

import com.example.brainstorm.courses.Course;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @SequenceGenerator(
            name = "message_sequence",
            sequenceName = "message_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "message_sequence"
    )
    private Long msg_id;
    @JsonBackReference(value = "course-msg")
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @Column(name = "username")
    private String username;
    @Column(name = "text")
    private String text;

    public Message(Long msg_id, Course course, String username, String text) {
        this.msg_id = msg_id;
        this.course = course;
        this.username = username;
        this.text = text;
    }

    public Message(Course course, String username, String text) {
        this.course = course;
        this.username = username;
        this.text = text;
    }

    public Message() {
    }

    public Long getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(Long msg_id) {
        this.msg_id = msg_id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msg_id=" + msg_id +
                ", course=" + course +
                ", username='" + username + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
