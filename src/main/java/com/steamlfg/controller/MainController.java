package com.steamlfg.controller;

import com.steamlfg.model.dto.AnnouncementDTO;
import com.steamlfg.model.dto.UserDTO;
import com.steamlfg.model.entity.Announcement;
import com.steamlfg.service.AnnouncementService;
import com.steamlfg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    AnnouncementService announcementService;
    @Autowired
    UserService userService;


    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping("/")
    public ModelAndView getAnnouncements(){
        ModelAndView modelAndView = new ModelAndView("index");
        List<UserDTO> users = userService.findAll();
        modelAndView.addObject("users",users);

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
