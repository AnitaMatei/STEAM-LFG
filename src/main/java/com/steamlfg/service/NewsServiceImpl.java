package com.steamlfg.service;

import com.steamlfg.model.dto.NewsDTO;
import com.steamlfg.model.entity.Game;
import com.steamlfg.model.entity.News;
import com.steamlfg.repository.GameRepository;
import com.steamlfg.repository.NewsRepository;
import com.steamlfg.utils.HttpClientGame;
import com.steamlfg.utils.ParseSteamData;
import org.modelmapper.ModelMapper;
import org.openid4java.server.ServerException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsRepository newsRepository;
    @Autowired
    GameRepository gameRepository;
    ModelMapper modelMapper;

    public NewsServiceImpl(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public List<NewsDTO> findAllBySteamAppOrderByDateDesc(String steamAppId) {
        List<NewsDTO> newsDTOS = new ArrayList<>();
        Game game = gameRepository.findTopBySteamAppId(steamAppId).get();

        if(newsRepository.findTopByGameByGameId(game).isEmpty()){
            addNewsForSteamApp(game);
            System.out.println("Adding news for "+game.getGameName());
        }
        List<News> newsList = newsRepository.findTop10ByGameByGameIdOrderByDateDesc(game);
        for(News news : newsList)
        {
            newsDTOS.add(modelMapper.map(news,NewsDTO.class));
        }

        return newsDTOS;
    }

    private void addNewsForSteamApp(Game game){
        List<News> newsList = new ArrayList<>();
        String gamesURI = "https://api.steampowered.com/ISteamNews/GetNewsForApp/v1?appid="+game.getSteamAppId();
        try {

            String gameString = new HttpClientGame(gamesURI).getAll();
            newsList = ParseSteamData.parseNewsList(gameString);

        } catch (ServerException e) {
            e.printStackTrace();
        }
        for(News news: newsList)
            news.setGameByGameId(game);

        newsRepository.saveAll(newsList);
    }
}
