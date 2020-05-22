package com.steamlfg.configuration;

import com.steamlfg.model.handler.CustomAuthenticationSuccessHandler;
import com.steamlfg.model.handler.CustomLogoutSuccessHandler;
import com.steamlfg.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

@Configuration
public class AppConfig {

    @Bean
    public AnnouncementService announcementService() {
        return new AnnouncementServiceImpl(modelMapper());
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(modelMapper());
    }

    @Bean
    public GameService gameService(){
        return new GameServiceImpl(modelMapper());
    }

    @Bean
    public CommentService commentService(){return new CommentServiceImpl(modelMapper());}

    @Bean
    public NewsService newsService(){
        return new NewsServiceImpl(modelMapper());
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public CustomLogoutSuccessHandler customLogoutSuccessHandler(){
        return new CustomLogoutSuccessHandler();
    }

}
