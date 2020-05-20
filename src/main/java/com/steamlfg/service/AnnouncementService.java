package com.steamlfg.service;

import com.steamlfg.model.dto.AnnouncementDTO;
import com.steamlfg.model.entity.Announcement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnnouncementService {
    AnnouncementDTO findById(Integer id);
    List<AnnouncementDTO> findAll();
    AnnouncementDTO addAnnouncement(String title, String description, String gameName);
    List<AnnouncementDTO> findAllByDate(Integer page);
    AnnouncementDTO findByAnnouncementHash(int hash);
}
