package com.steamlfg.model.entity;

import javax.persistence.*;

/*
TODO: create proper entity class after creating the db schemas
 */
@Entity
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "op_username")
    private String opUsername;
    @Column(name = "announcement_description")
    private String announcementDescription;

    public String getOpUsername() {
        return opUsername;
    }

    public void setOpUsername(String name) {
        this.opUsername = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getAnnouncementDescription() {
        return announcementDescription;
    }

    public void setAnnouncementDescription(String announcement) {
        this.announcementDescription = announcement;
    }
}
