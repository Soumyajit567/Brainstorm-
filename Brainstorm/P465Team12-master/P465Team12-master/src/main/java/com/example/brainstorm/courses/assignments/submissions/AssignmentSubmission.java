package com.example.brainstorm.courses.assignments.submissions;

import com.example.brainstorm.courses.Course;
import com.example.brainstorm.courses.assignments.Assignment;
import com.example.brainstorm.user.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.net.URL;
import java.sql.Date;

@Entity
@Table(name = "assignment_submission")
public class AssignmentSubmission {
    @Id
    @SequenceGenerator(
            name = "assignment_sequence",
            sequenceName = "assignment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "assignment_sequence"
    )
    @Column(name = "asgmt_sub_id", nullable = false)
    private Long asgmt_sub_id;

    @Column(name = "points_earned", nullable = true)
    private int points_earned;

    @JsonBackReference(value = "asgmt-sub")
    @ManyToOne
    @JoinColumn(name = "asgmt_id")
    private Assignment assignment;

    @JsonBackReference(value = "user-sub")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @Column(name = "sub_date", nullable = true)
    private Date date;

    @Column(name = "text_submission", nullable = true)
    private String textSubmission;

    @Column(name = "url", nullable = true)
    private URL url;

    public AssignmentSubmission(Long asgmt_sub_id, int points_earned, Assignment assignment, Users users, Date date, String textSubmission, URL url) {
        this.asgmt_sub_id = asgmt_sub_id;
        this.points_earned = points_earned;
        this.assignment = assignment;
        this.users = users;
        this.date = date;
        this.textSubmission = textSubmission;
        this.url = url;
    }

    public AssignmentSubmission(Long asgmt_sub_id) {
        this.asgmt_sub_id = asgmt_sub_id;
    }

    public AssignmentSubmission() {
        super();
    }

    public Long getAsgmt_sub_id() {
        return asgmt_sub_id;
    }

    public void setAsgmt_sub_id(Long asgmt_sub_id) {
        this.asgmt_sub_id = asgmt_sub_id;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getTextSubmission() {
        return textSubmission;
    }

    public void setTextSubmission(String textSubmission) {
        this.textSubmission = textSubmission;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

//    @Override
//    public String toString() {
//        return "AssignmentSubmission{" +
//                "asgmt_sub_id=" + asgmt_sub_id +
//                ", points_earned=" + points_earned +
//                ", assignment=" + assignment +
//                ", users=" + users +
//                ", date=" + date +
//                ", textSubmission='" + textSubmission + '\'' +
//                ", url=" + url +
//                '}';
//    }

    public int getPoints_earned() {
        return points_earned;
    }

    public void setPoints_earned(int points_earned) {
        this.points_earned = points_earned;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
