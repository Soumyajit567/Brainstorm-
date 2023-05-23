package com.example.brainstorm.courses.announcements;

import com.example.brainstorm.courses.announcements.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("anmts")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping()
    public List<Announcement> getAllAnnouncements() {
        return announcementService.getAllAnnouncements();
    }


    @PostMapping
    public ResponseEntity addAnnouncement(@RequestBody Announcement announcement) throws URISyntaxException {
        Announcement savedAnnouncement = announcementService.add(announcement);
        //may have to refactor the return, this is the example they gave
        return ResponseEntity.created(new URI("/anmt/" + savedAnnouncement.getAnnouncement_id())).body(savedAnnouncement);
    }
}
