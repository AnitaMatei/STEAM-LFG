package com.steamlfg.model.dto;


/*
TODO: create proper dto class after creating the db schemas
 */
public class AnnouncementDTO {
    private String opUsername;
    private String announcementDescription;

    public String getOpUsername() {
        return opUsername;
    }

    public void setOpUsername(String opUsername) {
        this.opUsername = opUsername;
    }

    public String getAnnouncementDescription() {
        return announcementDescription;
    }

    public void setAnnouncementDescription(String announcementDescription) {
        this.announcementDescription = announcementDescription;
    }
}
