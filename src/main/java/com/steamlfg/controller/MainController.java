package com.steamlfg.controller;

import com.steamlfg.model.dto.AnnouncementDTO;
import com.steamlfg.model.entity.Announcement;
import com.steamlfg.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {
    @Autowired()
    AnnouncementService announcementService;

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/announcements")
    public ModelAndView getAnnouncements(){
        ModelAndView modelAndView = new ModelAndView("announcements");
        List<AnnouncementDTO> announcements = announcementService.findAll();
        modelAndView.addObject("announcements",announcements);

        return modelAndView;
    }

    @PostMapping("/postAnnouncement")
    public ModelAndView postAnnouncement(@RequestParam("name") String name, @RequestParam("description") String announcementDesc) {
        ModelAndView modelAndView=new ModelAndView("announcement posted");
        announcementService.addAnnouncement(name,announcementDesc);

        modelAndView.addObject("name",name);
        modelAndView.addObject("description",announcementDesc);
        return modelAndView;
    }
}
