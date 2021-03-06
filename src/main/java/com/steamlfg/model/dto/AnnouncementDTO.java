package com.steamlfg.model.dto;


import com.steamlfg.model.entity.Game;
import com.steamlfg.model.entity.User;

import java.sql.Timestamp;
import java.util.Objects;

public class AnnouncementDTO {
    private String announcementTitle;
    private String announcementDescription;
    private Timestamp dateTime;
    private UserDTO userByUserId;
    private GameDTO gameByGameId;
    private Integer announcementHash;

    public String getAnnouncementTitle() {
        return announcementTitle;
    }

    public void setAnnouncementTitle(String announcementTitle) {
        this.announcementTitle = announcementTitle;
    }

    public String getAnnouncementDescription() {
        return announcementDescription;
    }

    public void setAnnouncementDescription(String announcementDescription) {
        this.announcementDescription = announcementDescription;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getAnnouncementHash() {
        return announcementHash;
    }

    public void setAnnouncementHash(Integer announcementHash) {
        this.announcementHash = announcementHash;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnnouncementDTO that = (AnnouncementDTO) o;
        return Objects.equals(announcementTitle, that.announcementTitle) &&
                Objects.equals(announcementDescription, that.announcementDescription) &&
                Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(announcementTitle, announcementDescription, dateTime);
    }

    public UserDTO getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserDTO userByUserId) {
        this.userByUserId = userByUserId;
    }

    public GameDTO getGameByGameId() {
        return gameByGameId;
    }

    public void setGameByGameId(GameDTO gameByGameId) {
        this.gameByGameId = gameByGameId;
    }
}
