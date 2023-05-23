package com.example.brainstorm.courses;

import com.example.brainstorm.courses.announcements.Announcement;
import com.example.brainstorm.courses.assignments.Assignment;
import com.example.brainstorm.courses.calendar.Event;
import com.example.brainstorm.courses.messages.Message;
import com.example.brainstorm.user.UserRepository;
import com.example.brainstorm.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("course")
public class CourseController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseService courseService;

    @GetMapping()
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }
    @GetMapping("/{course_id}")
    public Optional<Course> getCourse(@PathVariable Long course_id) {
        return courseService.getCourse(course_id);
    }


    @PostMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Course addCourse(@RequestBody Course course) {
        Course addedCourse = courseService.addCourse(course);
        return addedCourse;
    }
    @GetMapping("/anmt/{course_id}")
    public List<Announcement> getAllCourseAnnouncements(@PathVariable Long course_id) {
        return courseService.getAllCourseAnnouncements(course_id);
    }

//    @RequestMapping(value = "/anmt/{course_id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value="/anmt/{course_id}",  consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Announcement addAnnouncement(@PathVariable Long course_id, @RequestBody Announcement announcement) {
        Announcement addedAnnouncement = courseService.addAnnouncement(course_id, announcement);
        return addedAnnouncement;
    }

    @GetMapping("/calendar/{course_id}")
    public List<Event> getAllCourseEvents(@PathVariable Long course_id) {
        return courseService.getAllCourseEvents(course_id);
    }


    @PutMapping("/{course_id}")
    public ResponseEntity updateCourse(@PathVariable Long course_id, @RequestBody Course course) {
        Course currentCourse = courseService.getCourse(course_id).get();
        currentCourse.setTitle(course.getTitle());
        currentCourse.setDescription(course.getDescription());
        currentCourse = courseService.saveCourse(course);

        return ResponseEntity.ok(currentCourse);
    }

    @GetMapping("/asgmts/{course_id}")
    public List<Assignment> getAllCourseAssignments(@PathVariable Long course_id) {
        return courseService.getAllCourseAssignments(course_id);
    }

    @PostMapping(value = "/asgmts/{course_id}",  consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Assignment addAssignment(@PathVariable Long course_id, @RequestBody Assignment assignment) {
        Assignment addedAssignment = courseService.addAssignment(course_id, assignment);
        return addedAssignment;
    }

    @GetMapping("/msgs/{course_id}")
    public List<Message> getAllCourseMessages(@PathVariable Long course_id) {
        return courseService.getAllCourseMessages(course_id);
    }

    @PostMapping(value="/msgs/{course_id}",  consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Message addMessage(@PathVariable Long course_id, @RequestBody Message message) {
        System.out.println(message.toString());
        Message addedMessage = courseService.addMessage(course_id, message);
        System.out.println(addedMessage.toString());
        return addedMessage;
    }

    @RequestMapping(value = "/asgmts/{course_id}/{asgmt_id}/{asgmt_sub_id}/{points}", method = RequestMethod.PUT)
    public void gradeAssignmentSubmission(@PathVariable Long course_id,
                                                    @PathVariable Long asgmt_id,
                                                    @PathVariable Long asgmt_sub_id,
                                                    @PathVariable int points) {
        System.out.println("reached!");
        Course currentCourse = courseService.getCourse(course_id).get();
        for(int i = 0; i<currentCourse.getAssignments().size();i++) {
            if(currentCourse.getAssignments().get(i).getAsgmt_id() == asgmt_id) {
                //Assignment currentAssignment = currentCourse.getAssignments().get(i);
                //List<AssignmentSubmission> assignmentSubmissions = currentAssignment.getAsgmt_submissions();
                System.out.println("found an assignment");
                for(int j = 0; j < currentCourse.getAssignments().get(i).getAsgmt_submissions().size(); j++) {
                    if(currentCourse.getAssignments().get(i).getAsgmt_submissions().get(j).getAsgmt_sub_id() == asgmt_sub_id) {
                        currentCourse.getAssignments().get(i).getAsgmt_submissions().get(j).setPoints_earned(points);
                        Users u = currentCourse.getAssignments().get(i).getAsgmt_submissions().get(j).getUsers();
                        for (Enrollment enrollment : u.getEnrollment()) {
                            if (enrollment.getCourse().getCourse_id() == currentCourse.getCourse_id()) {
                                enrollment.calcGrade();
                                break;
                            }
                        }
                        System.out.println("found the assignment submission");
                        break;
                    }
                }
                break;
            }
        }


        final Course updatedCourse = courseService.saveCourse(currentCourse);
    }

//
//    @GetMapping("/test/{course_id}")
//    public List<LeaderboardUser> getEnrolledStudents(@PathVariable Long course_id) {
//        return courseService.getEnrolledStudents(course_id);
//    }


}
