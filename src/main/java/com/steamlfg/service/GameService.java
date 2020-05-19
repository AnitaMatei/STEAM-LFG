package com.steamlfg.service;

import com.steamlfg.model.dto.GameDTO;
import org.springframework.stereotype.Service;


@Service
public interface GameService {
   // void addGames();
    GameDTO findBySteamAppId(String steamAppId);
    GameDTO findByGameName(String name);
}
