package com.steamlfg.service;

import com.steamlfg.model.dto.AnnouncementDTO;
import com.steamlfg.model.dto.CommentDTO;
import com.steamlfg.model.dto.UserDTO;
import com.steamlfg.model.entity.Announcement;
import com.steamlfg.model.entity.Comment;
import com.steamlfg.model.principal.UserPrincipal;
import com.steamlfg.repository.AnnouncementRepository;
import com.steamlfg.repository.CommentRepository;
import com.steamlfg.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentServiceImpl implements CommentService {
    @Autowired
    AnnouncementRepository announcementRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDTO addComment(String messageTxt, int announcementHash) {
        Comment comment = new Comment();
        UserDTO user = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        Announcement announcement = announcementRepository.findByAnnouncementHash(announcementHash).get();

        comment.setAnnouncementByAnnouncementId(announcement);
        comment.setMessageDateTime(new Timestamp(new Date().getTime()));
        comment.setMessageTxt(messageTxt);
        comment.setUserByUserId(userRepository.findByOid(user.getOid()).get());

        return modelMapper.map(commentRepository.save(comment), CommentDTO.class);
    }

    @Override
    public List<CommentDTO> findAllByAnnouncementHashOrderByDateTimeDesc(Integer page, int announcementHash) {
        Pageable pageable = PageRequest.of(page,5);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        Announcement announcement = announcementRepository.findByAnnouncementHash(announcementHash).get();
        List<Comment> comments = commentRepository.findAllByAnnouncementByAnnouncementIdOrderByDateTimeDesc(pageable,announcement);

        for (Comment comment : comments) {
            commentDTOList.add(modelMapper.map(comment, CommentDTO.class));
        }

        return commentDTOList;
    }

}
