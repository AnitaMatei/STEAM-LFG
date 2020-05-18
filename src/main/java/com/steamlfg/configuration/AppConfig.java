package com.steamlfg.configuration;

import com.steamlfg.model.handler.CustomAuthenticationSuccessHandler;
import com.steamlfg.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class AppConfig {

    @Bean
    public AnnouncementService announcementService(){
        return new AnnouncementServiceImpl(modelMapper());
    }

    @Bean
    public UserService userService(){
        return new UserServiceImpl(modelMapper());
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler(){
        return new CustomAuthenticationSuccessHandler();
    }

}
