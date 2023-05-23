package com.example.brainstorm.courses.assignments;

import com.example.brainstorm.courses.Course;
import com.example.brainstorm.courses.Searchable;
import com.example.brainstorm.courses.assignments.submissions.AssignmentSubmission;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "assignment")
public class Assignment implements Searchable {
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
    @Column(name = "asgmt_id", nullable = false)
    private Long asgmt_id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "total_points", nullable = false)
    private Integer total_points;
//    @Column(name = "file", nullable = true)
//    private File file;
    @Column(name = "url", nullable = true)
    private URL url;
    @JsonBackReference(value = "course-asgmt")
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @JsonManagedReference(value = "asgmt-sub")
    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AssignmentSubmission> asgmt_submissions = new ArrayList<>();
    @Column(name = "due_date", nullable = true)
    private Date date;

    public Assignment(Long asgmt_id, String title, String description, URL url) {
        this.asgmt_id = asgmt_id;
        this.title = title;
        this.description = description;
        this.url = url;

    }

    public Assignment() {
        super();
    }

    public Long getAsgmt_id() {
        return asgmt_id;
    }

    public void setAsgmt_id(Long asgmt_id) {
        this.asgmt_id = asgmt_id;
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

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public Integer getTotal_points() {
        return total_points;
    }

    public void setTotal_points(Integer total_points) {
        this.total_points = total_points;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<AssignmentSubmission> getAsgmt_submissions() {
        return asgmt_submissions;
    }

    public void setAsgmt_submissions(List<AssignmentSubmission> asgmt_submissions) {
        this.asgmt_submissions = asgmt_submissions;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "asgmt_id=" + asgmt_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", total_points=" + total_points +
                ", url=" + url +
                ", course=" + course +
                ", asgmt_submissions=" + asgmt_submissions +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean contains(String searchedString) {
        return title.toLowerCase().contains(searchedString.toLowerCase()) || description.toLowerCase().contains(searchedString.toLowerCase());
    }
}
