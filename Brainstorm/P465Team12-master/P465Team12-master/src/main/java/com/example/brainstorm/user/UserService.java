package com.example.brainstorm.user;

import com.example.brainstorm.courses.Course;
import com.example.brainstorm.courses.DetailedCourse;
import com.example.brainstorm.courses.Enrollment;
import com.example.brainstorm.courses.Searchable;
import com.example.brainstorm.courses.announcements.Announcement;
import com.example.brainstorm.courses.announcements.AnnouncementController;
import com.example.brainstorm.courses.assignments.Assignment;
import com.example.brainstorm.courses.assignments.AssignmentRepository;
import com.example.brainstorm.courses.assignments.submissions.AssignmentSubmission;
import com.example.brainstorm.courses.assignments.submissions.AssignmentSubmissionRepository;
import com.example.brainstorm.courses.calendar.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    /**
     * The main logic layer. Called upon by
     * UserController.
     */

    private final UserRepository userRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private AssignmentSubmissionRepository assignmentSubmissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AnnouncementController annController;

    /**
     * <p>Constructor</p>
     * @param userRepository the data access layer for users
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * <p>
     * Registers a new user
     * Checks if a given username is already taken.
     * If not, encodes password and saves the new user.
     *</p>
     *
     * @param user a potential new user
     * @return the newly registered user
     * @throws Exception
     */
    public Users register(Users user) throws Exception {
        if (checkIfUserExists(user.getUsername())) {
            throw new Exception();
        }
        hashPassword(user);
        return userRepository.save(user);
    }


    /**
     * <p>
     * Checks if username exists in database.
     * If so, checks password to allow login.
     * </p>
     *
     * @param username the sent username
     * @param password the sent password
     * @return the logged-in user
     * @throws Exception
     */
    public Users login(String username, String password) throws Exception {
        Users existingUser = userRepository.findByUsername(username);
        if (existingUser == null) {
            System.out.println("user dne");
           //the user doesn't exist
            throw new Exception();
        } else {
            if (passwordEncoder.matches(password, existingUser.getPassword())) {
                return existingUser;
            } else {
                System.out.println("password mismatch");
                throw new Exception();
            }
        }
    }


    /**
     * Encodes password for storage. Uses hashing function.
     * @param user the user who's password will be hashed
     */
    public void hashPassword(Users user) {
        System.out.println("old pass: " + user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("New pass: " + user.getPassword());
    }

    /**
     * Checks if user exists within database.
     *
     * @param username the potenial user's username
     * @return true if user exists, false if not
     */
    private boolean checkIfUserExists(String username) {
        return userRepository.findByUsername(username) != null ? true : false;
    }

    /**
     * Service layer step for finding a User
     * with a given research token.
     *
     * @param token the user's reset token
     * @return Users object with given reset token
     */
//    public Users getByResetToken(String token){
//        return userRepository.findByResetToken(token);
//    }


    /**
     * <p>Get all the event's associated with a specific user</p>
     * @param user_id the specific user's id
     * @return a list of all events associated with the specific user
     */
    public List<Event> getUserEvents(Long user_id) {
        List<Enrollment> enrollment = userRepository.getById(user_id).getEnrollment();
        List<Event> eventsFromEnrollment = new ArrayList<>();
        for(int i = 0; i<enrollment.size();i++) {
            Course course = enrollment.get(i).getCourse();
            eventsFromEnrollment.addAll(course.getEvents());
        }
        return eventsFromEnrollment;

    }

//    public List<Event> getUserWeeklyEvents(Long user_id) {
//        List<Enrollment> enrollment = userRepository.getById(user_id).getEnrollment();
//        List<Event> eventsFromEnrollment = new ArrayList<>();
//        for(int i = 0; i<enrollment.size();i++) {
//            Course course = enrollment.get(i).getCourse();
//            eventsFromEnrollment.addAll(course.getEvents());
//        }
//        return eventsFromEnrollment;
//    }


    /**
     * <p>Get a list of all announcements associated with a user</p>
     * @param user_id the specific user's id
     * @return a list of all announcements associated with a user
     */
    public List<Announcement> getUserAnnouncements(long user_id){
        List<Enrollment> enrollment = userRepository.getById(user_id).getEnrollment();
        List<Announcement> announcementsFromEnrollment = new ArrayList<>();
        for(int i = 0; i<enrollment.size();i++) {
            Course course = enrollment.get(i).getCourse();
            announcementsFromEnrollment.addAll(course.getAnnouncements());
        }
        return announcementsFromEnrollment;
    }


    /**
     * <p>
     *     Returns all courses associated with the specific User
     * </p>
     *
     * @param user_id the user's unique ID
     * @return the list of all courses associated with this user
     */
    public List<Course> getUserCourses(Long user_id) {
        List<Enrollment> enrollment = userRepository.getById(user_id).getEnrollment();
        List<Course> courses = new ArrayList<>();
        for(int i = 0; i<enrollment.size();i++) {
            Course course = enrollment.get(i).getCourse();
            courses.add(course);
        }
        return courses;

    }
  
    @PostMapping
    public ResponseEntity addAnnouncement(@RequestBody Announcement announcement) throws URISyntaxException {
        return annController.addAnnouncement(announcement);
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }


    /**
     * Service Layer step for search functionality
     *
     * Given a searched string and user id, get all Searchable objects
     * for the given user
     *
     * @param user_id
     * @param searchedString
     * @return List of Searchable objects that contain searchString
     */
    public List<Searchable> getSearchEntities(Long user_id, String searchedString) {
        List<Course> courses = getUserCourses(user_id);
        List<Searchable> results = new ArrayList<>();
        for (Course course: courses) {
            for(Event event : course.getEvents()) {
                if (event.contains(searchedString)) results.add(event);
            }
            for(Announcement announcement : course.getAnnouncements()) {
                if (announcement.contains(searchedString)) results.add(announcement);
            }
            for(Assignment assignment : course.getAssignments()) {
                if (assignment.contains(searchedString)) results.add(assignment);
            }
        }
        return results;
    }

    public Users getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public List<Event> getSearchedEvents(Long user_id, String searchedString) {
        List<Course> courses = getUserCourses(user_id);
        List<Event> results = new ArrayList<>();
        for (Course course: courses) {
            for (Event event : course.getEvents()) {
                if (event.contains(searchedString)) results.add(event);
            }
        }
        return results;
    }

    public List<Announcement> getSearchedAnnouncements(Long user_id, String searchedString) {
        List<Course> courses = getUserCourses(user_id);
        List<Announcement> results = new ArrayList<>();
        for (Course course: courses) {
            for(Announcement announcement : course.getAnnouncements()) {
                if (announcement.contains(searchedString)) results.add(announcement);
            }
        }
        return results;
    }

    public List<Assignment> getSearchedAssignments(Long user_id, String searchedString) {
        List<Course> courses = getUserCourses(user_id);
        List<Assignment> results = new ArrayList<>();
        for (Course course: courses) {
            for(Assignment assignment : course.getAssignments()) {
                if (assignment.contains(searchedString)) results.add(assignment);
            }
        }
        return results;
    }

    public List<Assignment> getUserAssignments(Long user_id) {
        List<Enrollment> enrollment = userRepository.getById(user_id).getEnrollment();
        List<Assignment> assignmentsFromEnrollment = new ArrayList<>();
        for(int i = 0; i<enrollment.size();i++) {
            Course course = enrollment.get(i).getCourse();
            assignmentsFromEnrollment.addAll(course.getAssignments());
        }
        return assignmentsFromEnrollment;
    }

    /*
        added 11/14
        goal is to display a hashmap that maps a string (course title, such as p465) to a double (course grade, such as 92.0)
        and these understandably are found by the user_id
     */
    public HashMap<String, Double> getUserEnrollment(Long user_id){
        List<Enrollment> e = userRepository.getById(user_id).getEnrollment();
        HashMap<String, Double> courseGrades = new HashMap<>();
        for (int i = 0; i < e.size(); i++){
            String newTitle = e.get(i).getCourse().getTitle();
            double newGrade = e.get(i).getGrade();
            courseGrades.put(newTitle, newGrade);
        }
        return courseGrades;
    }

    /*
        added 11/14
        goal here is to display a list of user's submissions for a specific course
        heavily rudimentary, need to look over with other backend
     */
    public HashMap<Assignment, AssignmentSubmission> getUserSubmissionsForCourse(Long user_id, Long course_id){
        List<Course> courses = getUserCourses(user_id);
        Course searchedCourse = null;
        for (Course c : courses){
            if (c.getCourse_id() == course_id){
                searchedCourse = c;
            }
        }
        List<AssignmentSubmission> subs = userRepository.getById(user_id).getSubmissions();
        HashMap<Assignment, AssignmentSubmission> result = new HashMap<>();
        for (AssignmentSubmission sub : subs){
            if (sub.getAssignment().getCourse().getCourse_id() == searchedCourse.getCourse_id()) result.put(sub.getAssignment(), sub);
        }
        return result;
    }

    public List<AssignmentSubmission> getUserAssignmentSubmission(Long user_id) {
        Users user = userRepository.getById(user_id);
        return user.getSubmissions();
    }

    public List<DetailedCourse> getTest(Long user_id) {
        List<Enrollment> enrollment = userRepository.getById(user_id).getEnrollment();
        List<DetailedCourse> courses = new ArrayList<>();
        for(int i = 0; i<enrollment.size();i++) {
            Course course = enrollment.get(i).getCourse();
            double courseGrade = enrollment.get(i).getGrade();
            int coursePoints = enrollment.get(i).getPoints();
            String courseTitle = course.getTitle();
            String courseDescription = course.getDescription();
            courses.add(new DetailedCourse(course, courseGrade, coursePoints, courseTitle, courseDescription));
        }
        return courses;
    }

    public List<DetailedCourse> getDetailedCourse(Long user_id, Long course_id) {
        List<Enrollment> enrollments = userRepository.getById(user_id).getEnrollment();
        for(Enrollment enrollment : enrollments) {
            if(enrollment.getCourse().getCourse_id() == course_id) {
                /*

    private Course course;
    private double courseGrade;
    private int coursePoints;
    private String courseTitle;
    private String courseDescription;
    private Long courseID;
                 */
                double courseGrade = enrollment.getGrade();
                int coursePoints = enrollment.getPoints();
                String courseTitle = enrollment.getCourse().getTitle();
                String courseDescription = enrollment.getCourse().getDescription();
                Long courseID = enrollment.getCourse().getCourse_id();
                List<DetailedCourse> detailedCourses = new ArrayList<>();
                detailedCourses.add(new DetailedCourse(enrollment.getCourse(), courseGrade, coursePoints, courseTitle, courseDescription));
                return detailedCourses;
            }
        }
        return null;
    }

    public AssignmentSubmission addSubmission(Long asgmt_id, Long user_id, AssignmentSubmission submission){
        Users u = userRepository.getById(user_id);
        Assignment a = assignmentRepository.getById(asgmt_id);
        submission.setAssignment(a);
        submission.setUsers(u);
        assignmentSubmissionRepository.save(submission);
        u.addSubmission(submission, a.getCourse().getCourse_id());
        userRepository.save(u);
        return submission;
    }
}
