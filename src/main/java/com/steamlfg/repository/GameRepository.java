package com.steamlfg.repository;

import com.steamlfg.model.dto.GameDTO;
import com.steamlfg.model.entity.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends CrudRepository<Game,Integer> {
    Optional<Game> findTopBySteamAppId(String steamAppId);
    Optional<Game> findByGameName(String gameName);
    List<Game> findTop20ByGameNameContainsOrderByGameNameAsc(String gameName);
}
