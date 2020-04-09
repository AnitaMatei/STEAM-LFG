package com.steamlfg.configuration;

import com.steamlfg.service.AnnouncementService;
import com.steamlfg.service.AnnouncementServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public AnnouncementService announcementService(){
        return new AnnouncementServiceImpl(modelMapper());
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
