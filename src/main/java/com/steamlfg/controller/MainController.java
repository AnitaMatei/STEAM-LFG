package com.steamlfg.controller;

import com.steamlfg.model.dto.UserDTO;
import com.steamlfg.model.principal.UserPrincipal;
import com.steamlfg.service.AnnouncementService;
import com.steamlfg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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


    @GetMapping("/")
    public ModelAndView getAnnouncements(){
        ModelAndView modelAndView = createModelLoggedIn("index");
        List<UserDTO> users = userService.findAll();
        modelAndView.addObject("users", users);

        return modelAndView;
    }

    @PostMapping("/postAnnouncement")
    public ModelAndView postAnnouncement(@RequestParam("name") String name, @RequestParam("description") String announcementDesc) {
        ModelAndView modelAndView=new ModelAndView("announcement posted");
        return modelAndView;
    }

    private ModelAndView createModelLoggedIn(String file){
        ModelAndView model = new ModelAndView(file);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof UserPrincipal)){
            model.addObject("loginbutton","visible");
            return model;
        }else
            model.addObject("loginbutton","invisible");
        UserDTO user = ((UserPrincipal)principal).getUser();
        model.addObject("username",user.getUsername());
        model.addObject("avatar",user.getIconLink());
        model.addObject("steamprofile",user.getSteamProfile());
        return model;
    }
}
