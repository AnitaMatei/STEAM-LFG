package com.steamlfg.service;

import com.steamlfg.model.dto.AnnouncementDTO;
import com.steamlfg.model.entity.Announcement;
import com.steamlfg.repository.AnnouncementRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AnnouncementService {
    public AnnouncementDTO findById(Long id);
    public List<AnnouncementDTO> findAll();
    public void addAnnouncement(String opUsername, String announcementDescription);
}
