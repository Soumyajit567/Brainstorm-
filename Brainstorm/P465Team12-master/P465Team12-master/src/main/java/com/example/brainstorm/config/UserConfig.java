package com.example.brainstorm.config;


import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {


//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository repository, EventRepository eventRepository, CourseRepository courseRepository){
//        return args->{
//            Users john = new Users("jdoe","password","John","Doe","jdoe@email.com",
//                    "test1","test2", Roles.STUDENT, new ArrayList<>());
//            Users allie = new Users("abrock","password","Allie","Brock","abrock@email.com",
//                    "test1","test2", Roles.ADMIN, new ArrayList<>());
//            Course p465 = new Course("p465", "desc", null, null);
////            Announcement a = new Announcement( "hey!", "scott here", LocalDateTime.now());
//            Event compSci = new Event( p465,"CS lecture", "💿", LocalDateTime.now(), LocalDateTime.now());
//            Event gameDev = new Event( p465,"Game Dev Lab", "🎮", LocalDateTime.now(), LocalDateTime.now());
//            p465.setEvents(List.of(compSci, gameDev));
////            p465.setAnnouncements(List.of(a));
//            Enrollment e = new Enrollment(allie, p465);
//            allie.appendEnrollment(e);
//            courseRepository.saveAll(
//                    List.of(p465)
//              );
//            Users scott = new Users("scottwoz","password","Scott","Woz","scotty@email.com",
//                    "test1","test2", Roles.INSTRUCTOR, new ArrayList<>());
//            Enrollment e2 = new Enrollment(scott, p465);
//            scott.appendEnrollment(e2);
//            eventRepository.saveAll(
//                    List.of(compSci, gameDev)
//            );
//            repository.saveAll(
//                    List.of(john,allie)
//            );
//        };
//
//
//    }

}
