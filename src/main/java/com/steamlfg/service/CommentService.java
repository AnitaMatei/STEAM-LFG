package com.steamlfg.service;

import com.steamlfg.model.dto.CommentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    CommentDTO addComment(String messageTxt, int announcementHash);
    List<CommentDTO> findAllByAnnouncementHashOrderByDateTimeDesc(Integer page, int announcementHash);

}
