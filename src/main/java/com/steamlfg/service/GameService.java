package com.steamlfg.service;

import com.steamlfg.model.dto.GameDTO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface GameService {
    GameDTO findBySteamAppId(String steamAppId);
    GameDTO findByGameName(String name);
    List<GameDTO> findTop20ByGameNameContains(String gameName);
}
