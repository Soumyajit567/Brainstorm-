package com.example.brainstorm.courses;

import com.example.brainstorm.courses.announcements.Announcement;
import com.example.brainstorm.courses.assignments.Assignment;
import com.example.brainstorm.courses.calendar.Event;
import com.example.brainstorm.courses.messages.Message;
import com.example.brainstorm.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    public Optional<Course> getCourse(Long course_id) {
        return courseRepository.findById(course_id);
    }

    public List<Announcement> getAllCourseAnnouncements(Long course_id) {
        Optional<Course> course = courseRepository.findById(course_id);
        if (!course.isPresent()) {
            return null;
        }
        return course.get().getAnnouncements();
    }

    public List<Event> getAllCourseEvents(Long course_id) {
        Optional<Course> course = courseRepository.findById(course_id);
        if (!course.isPresent()) {
            return null;
        }
        return course.get().getEvents();
    }

//    public List<Event> getAllEventsInRange(Long course_id, String start, String end) throws ParseException {
//       return List.of(null);
//    }

    public Announcement addAnnouncement(Long course_id, Announcement announcement) {
        Course course = getCourse(course_id).get();
        announcement.setCourse(course);
        course.getAnnouncements().add(announcement);
        courseRepository.save(course);
        return announcement;
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Assignment> getAllCourseAssignments(Long course_id) {
        Optional<Course> course = courseRepository.findById(course_id);
        if (!course.isPresent()) {
            return null;
        }
        return course.get().getAssignments();
    }

    public List<Message> getAllCourseMessages(Long course_id) {
        Optional<Course> course = courseRepository.findById(course_id);
        if (!course.isPresent()) {
            return null;
        }
        return course.get().getMessages();
    }

    public Message addMessage(Long course_id, Message message) {
        Course course = getCourse(course_id).get();
        message.setCourse(course);
        course.getMessages().add(message);
        courseRepository.save(course);
        return message;
    }

    public Assignment addAssignment(Long course_id, Assignment assignment) {
        Course course = getCourse(course_id).get();
        assignment.setCourse(course);
        course.getAssignments().add(assignment);
        courseRepository.save(course);
        return assignment;
    }

    public Course addCourse(Course course) {
        courseRepository.save(course);
        return course;
    }

//    public List<LeaderboardUser> getEnrolledStudents(Long course_id) {
//        Course course = getCourse(course_id).get();
//        List<Enrollment> enrollments = course.getEnrollments();
//        List<LeaderboardUser> userList = new ArrayList<>();
//        for (Enrollment enrollment : enrollments) {
//            userList.add(new LeaderboardUser(enrollment.getUsers().getUsername(), enrollment.getPoints()));
//        }
//
//        return userList;
//    }

    
}
