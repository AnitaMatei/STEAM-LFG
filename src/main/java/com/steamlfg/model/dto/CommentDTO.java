package com.steamlfg.model.dto;

import com.steamlfg.model.entity.Announcement;
import com.steamlfg.model.entity.Comment;
import com.steamlfg.model.entity.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

public class CommentDTO {
    private Timestamp messageDateTime;
    private String messageTxt;
    private AnnouncementDTO announcementByAnnouncementId;
    private UserDTO userByUserId;

    public Timestamp getMessageDateTime() {
        return messageDateTime;
    }

    public void setMessageDateTime(Timestamp messageDateTime) {
        this.messageDateTime = messageDateTime;
    }

    public String getMessageTxt() {
        return messageTxt;
    }

    public void setMessageTxt(String messageTxt) {
        this.messageTxt = messageTxt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDTO comment = (CommentDTO) o;
        return Objects.equals(messageDateTime, comment.messageDateTime) &&
                Objects.equals(messageTxt, comment.messageTxt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageDateTime, messageTxt);
    }

    public AnnouncementDTO getAnnouncementByAnnouncementId() {
        return announcementByAnnouncementId;
    }

    public void setAnnouncementByAnnouncementId(AnnouncementDTO announcementByAnnouncementId) {
        this.announcementByAnnouncementId = announcementByAnnouncementId;
    }

    public UserDTO getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserDTO userByUserId) {
        this.userByUserId = userByUserId;
    }
}
