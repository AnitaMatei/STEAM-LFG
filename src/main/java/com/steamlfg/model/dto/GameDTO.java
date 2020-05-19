package com.steamlfg.model.dto;

import java.util.Objects;

public class GameDTO {
    private String gameName;
    private String gameIcon;
    private String steamAppId;


    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameIcon() {
        return gameIcon;
    }

    public void setGameIcon(String gameIcon) {
        this.gameIcon = gameIcon;
    }

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
        GameDTO game = (GameDTO) o;
        return Objects.equals(gameName, game.gameName) &&
                Objects.equals(gameIcon, game.gameIcon) &&
                Objects.equals(steamAppId, game.steamAppId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameName, gameIcon, steamAppId);
    }
}
