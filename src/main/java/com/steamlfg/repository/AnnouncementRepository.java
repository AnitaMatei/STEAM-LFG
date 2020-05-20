package com.steamlfg.repository;

import com.steamlfg.model.entity.Announcement;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnnouncementRepository extends CrudRepository<Announcement,Integer> {
    List<Announcement> findAllByOrderByDateTimeDesc(Pageable pageable);
    Optional<Announcement> findByAnnouncementHash(int announcementHash);
}
