package com.example.brainstorm.courses;

public class DetailedCourse {

    private Course course;
    private double courseGrade;
    private int coursePoints;
    private String courseTitle;
    private String courseDescription;
    private Long courseID;


    public DetailedCourse(Course course, double courseGrade, int coursePoints, String courseTitle, String courseDescription) {
        this.course = course;
        this.courseGrade = courseGrade;
        this.coursePoints = coursePoints;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.courseID = course.getCourse_id();
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public double getCourseGrade() {
        return courseGrade;
    }

    public void setCourseGrade(double courseGrade) {
        this.courseGrade = courseGrade;
    }

    public int getCoursePoints() {
        return coursePoints;
    }

    public void setCoursePoints(int coursePoints) {
        this.coursePoints = coursePoints;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public Long getCourseID() {
        return courseID;
    }

    public void setCourseID(Long courseID) {
        this.courseID = courseID;
    }
}
