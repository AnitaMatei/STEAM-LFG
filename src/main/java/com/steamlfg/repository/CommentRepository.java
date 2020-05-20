package com.steamlfg.repository;

import com.steamlfg.model.entity.Announcement;
import com.steamlfg.model.entity.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository  extends CrudRepository<Comment,Integer> {
    List<Comment> findAllByAnnouncementByAnnouncementIdOrderByMessageDateTimeDesc(Pageable pageable, Announcement announcement);
}
