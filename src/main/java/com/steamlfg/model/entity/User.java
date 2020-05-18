package com.steamlfg.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class User {
    private int userId;
    private String username;
    private String iconLink;
    private String oid;
    private String steamProfile;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "icon_link")
    public String getIconLink() {
        return iconLink;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }

    @Basic
    @Column(name = "oid")
    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    @Basic
    @Column(name = "steam_profile")
    public String getSteamProfile() {
        return steamProfile;
    }

    public void setSteamProfile(String steamProfile) {
        this.steamProfile = steamProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                Objects.equals(username, user.username) &&
                Objects.equals(iconLink, user.iconLink) &&
                Objects.equals(oid, user.oid) &&
                Objects.equals(steamProfile, user.steamProfile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, iconLink, oid, steamProfile);
    }
}
