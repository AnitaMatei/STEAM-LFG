package com.steamlfg.controller;

import com.steamlfg.model.dto.AnnouncementDTO;
import com.steamlfg.model.dto.CommentDTO;
import com.steamlfg.model.dto.UserDTO;
import com.steamlfg.model.principal.UserPrincipal;
import com.steamlfg.service.AnnouncementService;
import com.steamlfg.service.CommentService;
import com.steamlfg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    CommentService commentService;


    @GetMapping("/")
    public ModelAndView getIndexPage(){
        ModelAndView modelAndView = createModelLoggedIn("index");
        return modelAndView;
    }

    @GetMapping("/about")
    public ModelAndView getAboutPage(){
        ModelAndView modelAndView = createModelLoggedIn("about");
        return modelAndView;
    }

    @GetMapping("/announcements/{id}")
    public ModelAndView getAnnouncement(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("announcement");
        AnnouncementDTO announcementDTO = announcementService.findByAnnouncementHash(id);
        List<CommentDTO> commentDTOS = commentService.findAllByAnnouncementHashOrderByDateTimeDesc(0,announcementDTO.getAnnouncementHash());

        modelAndView.addObject("announcement_object",announcementDTO);
        modelAndView.addObject("comment_objects",commentDTOS);
        return modelAndView;
    }

    private ModelAndView createModelLoggedIn(String file){
        ModelAndView model = new ModelAndView(file);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof UserPrincipal)){
            model.addObject("loginbutton","visible");
            return model;
        }
        UserDTO user = ((UserPrincipal)principal).getUser();
        model.addObject("username",user.getUsername());
        model.addObject("avatar",user.getIconLink());
        model.addObject("steamprofile",user.getSteamProfile());
        return model;
    }
}
