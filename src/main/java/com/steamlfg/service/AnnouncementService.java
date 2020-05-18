package com.steamlfg.service;

import com.steamlfg.model.dto.AnnouncementDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnnouncementService {
    AnnouncementDTO findById(Long id);

    List<AnnouncementDTO> findAll();

    void addAnnouncement(String opUsername, String announcementDescription);
}
