package com.steamlfg.service;

import com.steamlfg.model.dto.GameDTO;
import com.steamlfg.model.entity.Game;
import com.steamlfg.repository.GameRepository;
import com.steamlfg.utils.HttpClientGame;
import com.steamlfg.utils.ParseSteamData;
import org.modelmapper.ModelMapper;
import org.openid4java.server.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameServiceImpl implements GameService {
    @Autowired
    GameRepository gameRepository;
    ModelMapper modelMapper;

    public GameServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public GameDTO findBySteamAppId(String steamAppId) {
        Optional<Game> game = gameRepository.findBySteamAppId(steamAppId);

        if(game.isEmpty())
            return null;
        return modelMapper.map(game,GameDTO.class);
    }

    @Override
    public GameDTO findByGameName(String name) {
        Optional<Game> game = gameRepository.findByGameName(name);

        if(game.isEmpty())
            return null;
        return modelMapper.map(game,GameDTO.class);
    }
//
//    @PostConstruct
//    @Override
//    public void addGames() {
//        List<Game> gameList = new ArrayList<>();
//        String gamesURI = "http://api.steampowered.com/ISteamApps/GetAppList/v0001/";
//        try {
//
//            String gameString = new HttpClientGame(gamesURI).getAll();
//            gameList = ParseSteamData.parseGameList(gameString);
//        } catch (ServerException e) {
//            e.printStackTrace();
//        }
//        //gameRepository.saveAll(gameList);
//    }
}
