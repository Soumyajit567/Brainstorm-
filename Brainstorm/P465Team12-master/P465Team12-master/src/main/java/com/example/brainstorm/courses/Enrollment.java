package com.example.brainstorm.courses;

import com.example.brainstorm.courses.assignments.Assignment;
import com.example.brainstorm.courses.assignments.submissions.AssignmentSubmission;
import com.example.brainstorm.user.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "enrollment")
public class Enrollment {
    @Id
    @SequenceGenerator(
            name = "enrollment_sequence",
            sequenceName = "enrollment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "enrollment_sequence"
    )
    private Long enrollment_id;
    @JsonBackReference(value = "user-enrollment")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
    @JsonBackReference(value = "course-enrollment")
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @Column(name = "grade", nullable = true)
    private double grade;
    @Column(name = "points", nullable = false)
    private int points;

    public Enrollment() {
        super();
    }

    public Enrollment(Users users, Course course) {
        this.users = users;
        this.course = course;
        this.points = 0;
    }

    public Long getEnrollment_id() {
        return enrollment_id;
    }

    public void setEnrollment_id(Long enrollment_id) {
        this.enrollment_id = enrollment_id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public void calcGrade(){
        Users u = getUsers();
        long earnedSum = 0;
        long totalSum = 0;
        for (AssignmentSubmission s : u.getSubmissions()){
            Assignment a = s.getAssignment();
            if (a.getCourse().getCourse_id() == this.getCourse().getCourse_id()){
                earnedSum += s.getPoints_earned();
                totalSum += a.getTotal_points();
            }
        }

        this.grade = (((double)earnedSum / (double)totalSum) * 100.0);
    }

    public int getPoints() { return points; }

    public void addPoints(int addition) { this.points += addition; }

    @Override
    public String toString() {
        return "Enrollment{" +
                "enrollment_id=" + enrollment_id +
                ", grade=" + grade +
                ", points=" + points +
                '}';
    }
}
