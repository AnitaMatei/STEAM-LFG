package com.steamlfg.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Comment {
    private Integer commentId;
    private Timestamp messageDateTime;
    private String messageTxt;
    private Announcement announcementByAnnouncementId;
    private User userByUserId;

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    @Basic
    @Column(name = "message_date_time")
    public Timestamp getMessageDateTime() {
        return messageDateTime;
    }

    public void setMessageDateTime(Timestamp messageDateTime) {
        this.messageDateTime = messageDateTime;
    }

    @Basic
    @Column(name = "message_txt")
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
        Comment comment = (Comment) o;
        return Objects.equals(commentId, comment.commentId) &&
                Objects.equals(messageDateTime, comment.messageDateTime) &&
                Objects.equals(messageTxt, comment.messageTxt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, messageDateTime, messageTxt);
    }

    @ManyToOne
    @JoinColumn(name = "announcement_id", referencedColumnName = "announcement_id", nullable = false)
    public Announcement getAnnouncementByAnnouncementId() {
        return announcementByAnnouncementId;
    }

    public void setAnnouncementByAnnouncementId(Announcement announcementByAnnouncementId) {
        this.announcementByAnnouncementId = announcementByAnnouncementId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
