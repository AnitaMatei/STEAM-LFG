package com.steamlfg.controller;

import com.steamlfg.model.dto.GameDTO;
import com.steamlfg.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/game")
public class GameController {
    @Autowired
    GameService gameService;

    @GetMapping("/search")
    List<GameDTO> searchGamesContainingString(@RequestParam String gameName){
        return gameService.findTop20ByGameNameContains(gameName);
    }

}
