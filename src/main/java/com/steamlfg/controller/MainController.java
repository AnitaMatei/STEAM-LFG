package com.steamlfg.controller;

import com.steamlfg.model.entity.Announcement;
import com.steamlfg.repository.AnnouncementRepository;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    AnnouncementRepository announcementRepository;

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/announcements")
    public ModelAndView getAnnouncements(){
        ModelAndView modelAndView = new ModelAndView("announcements");
        List<Announcement> announcements = (List<Announcement>) announcementRepository.findAll();
        System.out.println(announcements.get(0).getAnnouncement());
        modelAndView.addObject("announcements",announcements);

        return modelAndView;
    }

    @PostMapping("/postAnnouncement")
    public ModelAndView postAnnouncement(@RequestParam("name") String name, @RequestParam("description") String announcementDesc) {
        ModelAndView modelAndView=new ModelAndView("announcement posted");

        Announcement announcement=new Announcement();
        announcement.setId(3);
        announcement.setName(name);
        announcement.setAnnouncement(announcementDesc);
        announcementRepository.save(announcement);

        modelAndView.addObject("name",name);
        modelAndView.addObject("description",announcementDesc);
        return modelAndView;
    }
}
