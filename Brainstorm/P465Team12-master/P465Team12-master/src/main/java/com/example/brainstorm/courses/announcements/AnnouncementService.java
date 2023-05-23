package com.example.brainstorm.courses.announcements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;
    public Announcement add(Announcement announcement) {
        return announcementRepository.save(announcement);
    }

    public List<Announcement> getAllAnnouncements() { return announcementRepository.findAll();}
}
