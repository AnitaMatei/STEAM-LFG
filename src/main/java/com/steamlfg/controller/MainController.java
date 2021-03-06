package com.steamlfg.controller;

import com.steamlfg.model.dto.AnnouncementDTO;
import com.steamlfg.model.dto.CommentDTO;
import com.steamlfg.model.dto.NewsDTO;
import com.steamlfg.model.dto.UserDTO;
import com.steamlfg.model.principal.UserPrincipal;
import com.steamlfg.service.AnnouncementService;
import com.steamlfg.service.CommentService;
import com.steamlfg.service.NewsService;
import com.steamlfg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    NewsService newsService;
    @Autowired
    private SessionRegistry sessionRegistry;


    @GetMapping("/")
    public ModelAndView getIndexPage() {
        ModelAndView modelAndView = createModelLoggedIn("index");
        return modelAndView;
    }

    @GetMapping("/about")
    public ModelAndView getAboutPage() {
        ModelAndView modelAndView = createModelLoggedIn("about");
        return modelAndView;
    }

    @GetMapping("/announcements/{id}")
    public ModelAndView getAnnouncement(@PathVariable int id) {
        ModelAndView modelAndView = createModelLoggedIn("announcement");
        ;
        AnnouncementDTO announcementDTO = announcementService.findByAnnouncementHash(id);
        List<CommentDTO> commentDTOS = commentService
                .findAllByAnnouncementHashOrderByMessageDateTimeDesc(0, announcementDTO.getAnnouncementHash());
        for (CommentDTO commentDTO : commentDTOS) {
            System.out.println(commentDTO.getUserByUserId().getUsername());
        }

        modelAndView.addObject("announcement_object", announcementDTO);
        modelAndView.addObject("comment_objects", commentDTOS);
        return modelAndView;
    }

    @GetMapping("/online-users")
    private ModelAndView getOnlineUsers() {
        ModelAndView modelAndView = createModelLoggedIn("online-users");
        List<UserPrincipal> userPrincipals = (List<UserPrincipal>) (List) sessionRegistry.getAllPrincipals();
        modelAndView.addObject("users", userPrincipals);

        return modelAndView;

    }

    @GetMapping("/news")
    private ModelAndView getGameNews(
            @RequestParam(value = "appId", required = false) String steamAppId) {
        ModelAndView modelAndView = createModelLoggedIn("news");
        boolean hasNews;

        if (steamAppId == null) {
            hasNews = false;
            System.out.println("returning empty search bar");
        } else {
            hasNews = true;
            List<NewsDTO> newsDTOS = newsService.findAllBySteamAppOrderByDateDesc(steamAppId);
            modelAndView.addObject("news_objects", newsDTOS);
        }
        modelAndView.addObject("hasNews", hasNews);

        return modelAndView;

    }


    private ModelAndView createModelLoggedIn(String file) {
        ModelAndView model = new ModelAndView(file);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof UserPrincipal)) {
            model.addObject("loginbutton", "visible");
            return model;
        }
        UserDTO user = ((UserPrincipal) principal).getUser();
        model.addObject("username", user.getUsername());
        model.addObject("avatar", user.getIconLink());
        model.addObject("steamprofile", user.getSteamProfile());
        return model;
    }
}
