package com.steamlfg.model.dto;

import com.steamlfg.model.entity.User;

import javax.persistence.*;
import java.util.Objects;

public class UserDTO {
    private String username;
    private String iconLink;
    private String oid;
    private String steamProfile;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIconLink() {
        return iconLink;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }

    public String getSteamProfile() {
        return steamProfile;
    }

    public void setSteamProfile(String steamProfile) {
        this.steamProfile = steamProfile;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO user = (UserDTO) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(iconLink, user.iconLink) &&
                Objects.equals(oid, user.oid) &&
                Objects.equals(steamProfile, user.steamProfile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, iconLink, oid, steamProfile);
    }

}
