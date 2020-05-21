package com.steamlfg.controller;

import com.steamlfg.model.principal.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Map;

@Controller
public class LoginController {


    @GetMapping("/login")
    public ModelAndView login(@RequestParam Map<String, String> status) {
        ModelAndView model = new ModelAndView("login");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (status.get("status") != null) {
            if (status.get("status").equals("failed"))
                model.addObject("loginmsg", "Login with steam has failed");
            else if (status.get("status").equals("success"))
                model.addObject("loginmsg", "Login with steam has succeeded");
        } else if(principal instanceof UserPrincipal) {
            model.addObject("loginmsg","you're already logged in");
        }
        else{
            model.addObject("loginmsg", "Select login method");
            model.addObject("steambutton", "visible");
        }
        return model;
    }



}
