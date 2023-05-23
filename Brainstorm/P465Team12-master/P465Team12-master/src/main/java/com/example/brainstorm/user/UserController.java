package com.example.brainstorm.user;

import com.duosecurity.Client;
import com.duosecurity.exception.DuoException;
import com.duosecurity.model.Token;
import com.example.brainstorm.courses.Course;
import com.example.brainstorm.courses.DetailedCourse;
import com.example.brainstorm.courses.Searchable;
import com.example.brainstorm.courses.announcements.Announcement;
import com.example.brainstorm.courses.assignments.Assignment;
import com.example.brainstorm.courses.assignments.submissions.AssignmentSubmission;
import com.example.brainstorm.courses.calendar.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RestController
//@RestController
///*
//    Above tag allows this class to serve as a RESTful API.
// */

//seemingly, @RestController helped it mount to the front-end, i have no idea why
@RestController
//all mappings are tied to this mapping
@RequestMapping("/user")
public class UserController {
    /**
     * A Controller for Users that lets them interact with the site.
     * Consider this the API layer.
     * @return
     */
    @Value("${duo.api.host}")
    private String apiHost;
    @Value("${duo.clientId}")
    private String clientId;
    @Value("${duo.clientSecret}")
    private String clientSecret;
    @Value("${duo.redirect.uri}")
    private String redirectUri;
    @Value("${duo.failmode}")
    private String failmode;
    private Map<String, String> stateMap;
    private Client duoClient;
    private final UserService userService;

    @Autowired
    /*
        The final userService will be autowired to the
        constructor's UserService.

        (UserService is a Service Component, or a bean, so
        this works.)
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * Create and initialize the Duo Client.
     *
     * @throws DuoException For problems creating the Clients
     */
    @PostConstruct
    public void initializeDuoClient() throws DuoException {
        System.out.println("initalized a duo client");
        stateMap = new HashMap<>();
        duoClient = new Client.Builder(clientId, clientSecret, apiHost, redirectUri).build();

    }



    /**
     * API layer for a user's login.
     */
    @GetMapping("/login/{username}/{password}")
    public ResponseEntity<Void> login(@PathVariable(name = "username") String username,
                       @PathVariable(name = "password") String password) throws Exception {
        Users user = userService.login(username, password);
        try {
            duoClient.healthCheck();
        } catch (DuoException e) {
            e.printStackTrace();
        }
        System.out.println("healthy");
        //get this user's session identifer; used to validate duo responses
        String state = duoClient.generateState();
        System.out.println("Duo"+state);
        stateMap.put(state, user.getUsername());
        //redirect to duo's auth service
        String authUrl = duoClient.createAuthUrl(user.getUsername(), state);
        System.out.println("DuoAuth: "+authUrl);

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(authUrl)).build();
    }

    @GetMapping("/logintest/{username}/{password}")
    public Users loginTest(@PathVariable(name = "username") String username,
                           @PathVariable(name = "password") String password) throws Exception {
        Users user = userService.login(username, password);
        System.out.println(user.toString());
        return user;
    }

    @PostMapping(value = "/register",  consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Users register(@RequestBody Users user) throws Exception {
        return userService.register(user);
    }
    /**
     * GET handler for duo-callback page.
     *
     * @param duoCode    An authentication session transaction id
     * @param state   A random string returned from Duo used to maintain state
     *
     * @return String  A string that contains information about where to redirect next
     */
    @RequestMapping (value = "/duo-callback", method =RequestMethod.GET)
    public Users duoCallback(@RequestParam(name = "duo_code") String duoCode,
                                    @RequestParam(name = "state") String state) throws Exception {

        //check if stateMap has a key of @RequestParam state
        System.out.println("entered callback");
        if (!stateMap.containsKey(state)) {
            throw new Exception("does not exist in statemap");
        }
        // remove state from the list of valid sessions
        String username = stateMap.remove(state);
        //exchange the auth duoCode for a Token object
        Token token = duoClient.exchangeAuthorizationCodeFor2FAResult(duoCode, username);

        // If the auth was successful, render the login_success page otherwise return an error
        if (authWasSuccessful(token)) {
            System.out.println("auth success");
            return userService.getUser(username);
        } else {
            throw new Exception("auth not successful");
        }
    }


    /*
        Checks if Token given exists and its's authorization result is not null
     */
    private boolean authWasSuccessful(Token token) {
        if (token != null && token.getAuth_result() != null) {
            return "ALLOW".equalsIgnoreCase(token.getAuth_result().getStatus());
        }
        return false;
    }


//    /**
//     * Displays registration form.
//     */
//    /*
//    @GetMapping("/register")
//    public Users showRegistrationForm(Model model) {
////        model.addAttribute("user", new Users());
////        return "signup_form";
//        Users val = new Users("val","password","Val","Lang","vallangk@email.com",
//                "test1","test2", Users.Roles.ADMIN, new ArrayList<>());
//        return userService.addUser(val);
//    }
//
//    /**
//     * API layer for user registration.
//     */
//    @PostMapping("/process_register")
//    public String processRegister(Users user, final BindingResult bindResult, final Model model) {
//        try {
//            System.out.println(user.toString());
//            userService.register(user);
//        } catch (Exception e) {
//            bindResult.rejectValue("username", "userData.username","An account already exists for this username.");
//            model.addAttribute("registrationForm", user);
//            return "signup_form";
//        }
//        return "register_success";
//    }
//    */


    //gets all events connected to a user ID
    @GetMapping("/calendar/{user_id}")
    public List<Event> getUserEvents(@PathVariable Long user_id) {
        return userService.getUserEvents(user_id);
    }

    //TODO FIX SPELLING
    //gets all announcement connected to a user ID
    @GetMapping("/anmt/{user_id}")
    public List<Announcement> getUserAnnouncements(@PathVariable Long user_id) {
        return userService.getUserAnnouncements(user_id);
    }

    //gets all courses connected to a user ID
    @GetMapping("/course/{user_id}")
    public List<Course> getUserCourses(@PathVariable Long user_id) {
        return userService.getUserCourses(user_id);
    }

    @GetMapping()
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    //TODO MAKE WEEKLY ANNOUNCEMENT?
//    @GetMapping("/calendar/{user_id}/weekly")
//    public List<Event> getUserWeeklyEvents(@PathVariable Long user_id) {
//        return userService.getUserWeeklyEvents(user_id);
//    }

    //not exactly sure how this works yet. goes usercontroller > userservice > announcementcontroller > announcementservice
    @PostMapping
    public ResponseEntity addAnnouncement(@RequestBody Announcement announcement) throws URISyntaxException {
        return userService.addAnnouncement(announcement);
    }

    /**
     * API call to get all user specific Event objects using a String
     * @param user_id the id of the user
     * @param searchedString a string typed by the user, used to filter out objects
     * @return a list of all related objects
     */
    @GetMapping("/search/{user_id}/{searchedString}")
    public List<Searchable> getSearch(@PathVariable Long user_id, @PathVariable String searchedString) {
        return userService.getSearchEntities(user_id, searchedString);
    }

    /**
     * API call to get all user specific Event objects using a String
     * @param user_id the id of the user
     * @param searchedString a string typed by the user, used to filter out objects
     * @returna a list of all related objects
     */
    @GetMapping("/search/{user_id}/events/{searchedString}")
    public List<Event> getEventsFromSearch(@PathVariable Long user_id, @PathVariable String searchedString) {
        return userService.getSearchedEvents(user_id, searchedString);
    }

    /**
     * API call to get all user specific Event objects using a String
     * @param user_id the id of the user
     * @param searchedString a string typed by the user, used to filter out objects
     * @return a a list of all related objects
     */
    @GetMapping("/search/{user_id}/anmts/{searchedString}")
    public List<Announcement> getAnmtsFromSearch(@PathVariable Long user_id, @PathVariable String searchedString) {
        return userService.getSearchedAnnouncements(user_id, searchedString);
    }

    @GetMapping("/search/{user_id}/asgmts/{searchedString}")
    public List<Assignment> getAssignmentsFromSearch(@PathVariable Long user_id, @PathVariable String searchedString) {
        return userService.getSearchedAssignments(user_id, searchedString);
    }

    @GetMapping("/asgmt/{user_id}")
    public List<Assignment> getUserAssignments(@PathVariable Long user_id) {
        return userService.getUserAssignments(user_id);
    }

    @GetMapping("/asgmt-sub/{user_id}")
    public List<AssignmentSubmission> getUserAssignmentSubmissions(@PathVariable Long user_id) {
        return userService.getUserAssignmentSubmission(user_id);
    }
    @GetMapping("/asgmt/{course_id}/{user_id}")
    public HashMap<Assignment, AssignmentSubmission> getUserSubmissionsForCourse(@PathVariable Long user_id,
                                                                                 @PathVariable Long course_id) {
        return userService.getUserSubmissionsForCourse(user_id, course_id);
    }

    @GetMapping("/detailed-course/{user_id}")
    public List<DetailedCourse> test(@PathVariable Long user_id) {
        return userService.getTest(user_id);
    }

    @GetMapping("/detailed-course/{user_id}/{course_id}")
    public List<DetailedCourse> getSpecificDetailedCourse(@PathVariable Long user_id,
                                                    @PathVariable Long course_id) {
        return userService.getDetailedCourse(user_id, course_id);
    }
}
