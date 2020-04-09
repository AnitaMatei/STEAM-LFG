package com.steamlfg.service;

import com.steamlfg.model.dto.AnnouncementDTO;
import com.steamlfg.model.entity.Announcement;
import com.steamlfg.repository.AnnouncementRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementRepository announcementRepository;
    private ModelMapper modelMapper;

    public AnnouncementServiceImpl(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public AnnouncementDTO findById(Long id){
        Optional<Announcement> announcement=announcementRepository.findById(id);
        AnnouncementDTO announcementDTO;

        if(announcement.isEmpty())
        {
            announcementDTO=new AnnouncementDTO();
            announcementDTO.setAnnouncementDescription("-");
            announcementDTO.setOpUsername("not found");
        }else{
            announcementDTO = modelMapper.map(announcement.get(),AnnouncementDTO.class);
        }
        return announcementDTO;
    }

    @Override
    public List<AnnouncementDTO> findAll() {
        Iterable<Announcement> announcements = announcementRepository.findAll();
        List<AnnouncementDTO> announcementDTOList = new ArrayList<>();

        for(Announcement announcement : announcements){
            announcementDTOList.add(modelMapper.map(announcement,AnnouncementDTO.class));
        }

        return announcementDTOList;
    }

    @Override
    public void addAnnouncement(String opUsername, String announcementDescription) {
        Announcement announcement=new Announcement();
        announcement.setOpUsername(opUsername);
        announcement.setAnnouncementDescription(announcementDescription);

        announcementRepository.save(announcement);
    }
}
