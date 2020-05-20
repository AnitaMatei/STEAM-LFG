package com.steamlfg.controller;

import com.steamlfg.model.dto.AnnouncementDTO;
import com.steamlfg.service.AnnouncementService;
import com.steamlfg.service.AnnouncementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/announcement")
public class AnnouncementController {
    @Autowired
    AnnouncementService announcementService;

    @GetMapping("/{id}")
    AnnouncementDTO getAnnouncementById(@PathVariable Integer id) {
        return announcementService.findById(id);
    }

    @PostMapping("/add")
    void addAnnouncement(@RequestParam Map<String, String> query) {
        announcementService.addAnnouncement(query.get("title"), query.get("description"), query.get("gameName"));
    }

    @GetMapping("/page/{id}")
    List<AnnouncementDTO> getLastAnnouncementByPage(@PathVariable Integer id) {
        if (id < 0)
            return null;
        else return announcementService.findAllByDate(id);
    }

}
