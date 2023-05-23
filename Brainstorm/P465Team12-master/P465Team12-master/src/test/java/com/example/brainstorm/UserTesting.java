//package com.example.brainstorm;
//
//
//import com.example.brainstorm.courses.Course;
//import com.example.brainstorm.courses.Enrollment;
//import com.example.brainstorm.courses.announcements.Announcement;
//import com.example.brainstorm.courses.calendar.Event;
//import com.example.brainstorm.user.Users;
//import org.junit.Assert;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserTesting {
//
//    @Test
//    public void userTests() {
//        Users john = new Users("jdoe","password","John","Doe","jdoe@email.com",
//                    "test1","test2", Users.Roles.STUDENT, new ArrayList<>());
//        Users allie = new Users("abrock","password","Allie","Brock","abrock@email.com",
//                    "test1","test2", Users.Roles.ADMIN, new ArrayList<>());
//        allie.setUser_id(1l);
//        Course p465 = new Course("p465", "desc", null, null);
//        Announcement a = new Announcement( "hey!", "scott here", LocalDateTime.now());
//        Event compSci = new Event( p465,"CS lecture", "💿", LocalDateTime.now(), LocalDateTime.now());
//        Event gameDev = new Event( p465,"Game Dev Lab", "🎮", LocalDateTime.now(), LocalDateTime.now());
//        p465.setEvents(List.of(compSci, gameDev));
//        p465.setAnnouncements(List.of(a));
//        Enrollment e = new Enrollment(allie, p465);
//        allie.appendEnrollment(e);
//        Assert.assertEquals(allie.getEnrollment(), List.of(e));
//        Assert.assertEquals(allie.getEnrollment().get(0).getCourse(), p465);
//        Assert.assertEquals(allie.getEnrollment().get(0).getUsers(), allie);
//        Assert.assertEquals(allie.getEnrollment().get(0).getCourse().getEvents(), List.of(compSci, gameDev));
//        Assert.assertEquals(allie.getEnrollment().get(0).getCourse().getAnnouncements(), List.of(a));
//        Assert.assertEquals(p465.getEvents(), List.of(compSci, gameDev));
//        Assert.assertEquals(p465.getAnnouncements(), List.of(a));
//    }
//
//}
