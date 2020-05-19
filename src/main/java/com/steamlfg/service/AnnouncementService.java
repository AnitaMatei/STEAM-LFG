package com.steamlfg.service;

import com.steamlfg.model.dto.AnnouncementDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnnouncementService {
    public AnnouncementDTO findById(Integer id);
    public List<AnnouncementDTO> findAll();
    public void addAnnouncement(String title, String description, String gameName);
    public List<AnnouncementDTO> findAllByDate(Integer page);
}
