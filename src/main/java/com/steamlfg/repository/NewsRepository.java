package com.steamlfg.repository;

import com.steamlfg.model.entity.Game;
import com.steamlfg.model.entity.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends CrudRepository<News,Integer> {
    List<News> findTop10ByGameByGameIdOrderByDateDesc(Game game);
    Optional<News> findTopByGameByGameId(Game game);
}
