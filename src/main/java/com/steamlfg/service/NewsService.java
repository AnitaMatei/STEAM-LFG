package com.steamlfg.service;

import com.steamlfg.model.dto.NewsDTO;
import com.steamlfg.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NewsService {
    List<NewsDTO> findAllBySteamAppOrderByDateDesc(String steamAppId);

}
