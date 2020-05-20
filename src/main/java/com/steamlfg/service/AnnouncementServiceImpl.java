package com.steamlfg.service;

import com.steamlfg.model.dto.AnnouncementDTO;
import com.steamlfg.model.dto.UserDTO;
import com.steamlfg.model.entity.Announcement;
import com.steamlfg.model.entity.Game;
import com.steamlfg.model.entity.User;
import com.steamlfg.model.principal.UserPrincipal;
import com.steamlfg.repository.AnnouncementRepository;
import com.steamlfg.repository.GameRepository;
import com.steamlfg.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementRepository announcementRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameRepository gameRepository;
    private ModelMapper modelMapper;

    public AnnouncementServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AnnouncementDTO findById(Integer id) {
        Optional<Announcement> announcement = announcementRepository.findById(id);
        AnnouncementDTO announcementDTO;

        if (announcement.isEmpty())
            return null;

        announcementDTO = modelMapper.map(announcement.get(), AnnouncementDTO.class);
        return announcementDTO;
    }

    @Override
    public List<AnnouncementDTO> findAll() {
        Iterable<Announcement> announcements = announcementRepository.findAll();
        List<AnnouncementDTO> announcementDTOList = new ArrayList<>();

        for (Announcement announcement : announcements) {
            announcementDTOList.add(modelMapper.map(announcement, AnnouncementDTO.class));
        }

        return announcementDTOList;
    }

    @Override
    public List<AnnouncementDTO> findAllByDate(Integer page) {
        Pageable pageable = PageRequest.of(page,5);
        List<AnnouncementDTO> announcementDTOList = new ArrayList<>();
        List<Announcement> announcements = announcementRepository.findAllByOrderByDateTimeDesc(pageable);

        for (Announcement announcement : announcements) {
            announcementDTOList.add(modelMapper.map(announcement, AnnouncementDTO.class));
        }

        return announcementDTOList;
    }

    @Override
    public AnnouncementDTO addAnnouncement(String title, String description, String gameName) {
        Announcement announcement = new Announcement();
        UserDTO user = ((UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        announcement.setAnnouncementDescription(description);
        announcement.setAnnouncementTitle(title);
        announcement.setDateTime(new Timestamp(new Date().getTime()));
        announcement.setUserByUserId(userRepository.findByOid(user.getOid()).get());
        announcement.setAnnouncementHash(announcement.hashCode());

        announcement.setGameByGameId(gameRepository.findByGameName(gameName).get());
        return modelMapper.map(announcementRepository.save(announcement),AnnouncementDTO.class);
    }

    @Override
    public AnnouncementDTO findByAnnouncementHash(int announcementHash) {
        Optional<Announcement> announcement = announcementRepository.findByAnnouncementHash(announcementHash);
        AnnouncementDTO announcementDTO;

        if (announcement.isEmpty())
            return null;

        announcementDTO = modelMapper.map(announcement.get(), AnnouncementDTO.class);
        return announcementDTO;
    }
}
