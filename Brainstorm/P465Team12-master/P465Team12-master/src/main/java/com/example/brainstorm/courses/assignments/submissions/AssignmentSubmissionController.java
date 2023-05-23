package com.example.brainstorm.courses.assignments.submissions;

import com.example.brainstorm.courses.assignments.Assignment;
import com.example.brainstorm.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("asgmt-sub")
public class AssignmentSubmissionController {

    @Autowired
    private AssignmentSubmissionRepository assignmentSubmissionRepository;

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<AssignmentSubmission> getAllAssignmentSubmissions() { return assignmentSubmissionRepository.findAll();}

    @PostMapping(value = "/{asgmt_id}/{user_id}",  consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AssignmentSubmission addAssignmentSubmission(@PathVariable Long asgmt_id, @PathVariable Long user_id, @RequestBody AssignmentSubmission submission) {
        AssignmentSubmission newSub = userService.addSubmission(asgmt_id, user_id, submission);
        return newSub;
    }
}
