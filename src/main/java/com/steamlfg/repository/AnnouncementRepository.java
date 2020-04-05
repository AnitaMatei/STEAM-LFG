package com.steamlfg.repository;

import com.steamlfg.model.entity.Announcement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends CrudRepository<Announcement,Long> {
}
