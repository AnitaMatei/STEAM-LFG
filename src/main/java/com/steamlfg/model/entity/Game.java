package com.steamlfg.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Game {
    private Integer gameId;
    private String gameName;
    private String gameIcon;
    private String steamAppId;

    @Id
    @Column(name = "game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    @Basic
    @Column(name = "game_name")
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Basic
    @Column(name = "game_icon")
    public String getGameIcon() {
        return gameIcon;
    }

    public void setGameIcon(String gameIcon) {
        this.gameIcon = gameIcon;
    }

    @Basic
    @Column(name = "steam_app_id")
    public String getSteamAppId() {
        return steamAppId;
    }

    public void setSteamAppId(String steamAppId) {
        this.steamAppId = steamAppId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(gameId, game.gameId) &&
                Objects.equals(gameName, game.gameName) &&
                Objects.equals(gameIcon, game.gameIcon) &&
                Objects.equals(steamAppId, game.steamAppId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, gameName, gameIcon, steamAppId);
    }
}
