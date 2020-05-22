package com.steamlfg.controller;

import com.steamlfg.model.dto.NewsDTO;
import com.steamlfg.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    @Autowired
    NewsService newsService;


    @GetMapping("/{id}")
    List<NewsDTO> getNewsBySteamAppId(@PathVariable String id){
        return newsService.findAllBySteamAppOrderByDateDesc(id);
    }

}
