package com.steamlfg.service;

import com.steamlfg.model.dto.GameDTO;
import com.steamlfg.model.entity.Game;
import com.steamlfg.repository.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

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
        Optional<Game> game = gameRepository.findTopBySteamAppId(steamAppId);

        if(game.isEmpty())
            return null;
        return modelMapper.map(game,GameDTO.class);
    }

    @Override
    public List<GameDTO> findTop20ByGameNameContains(String gameName) {
        List<Game> games = gameRepository.findTop20ByGameNameContainsOrderByGameNameAsc(gameName);
        List<GameDTO> gameDTOS = new ArrayList<>();
        for(Game game : games){
            gameDTOS.add(modelMapper.map(game,GameDTO.class));
        }

        return gameDTOS;
    }

    @Override
    public GameDTO findByGameName(String name) {
        Optional<Game> game = gameRepository.findByGameName(name);

        if(game.isEmpty())
            return null;
        return modelMapper.map(game,GameDTO.class);
    }

}
