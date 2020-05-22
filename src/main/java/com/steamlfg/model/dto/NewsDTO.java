package com.steamlfg.model.dto;

import java.sql.Timestamp;
import java.util.Objects;

public class NewsDTO {
    private String content;
    private String title;
    private Timestamp date;
    private GameDTO gameByGameId;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsDTO news = (NewsDTO) o;
        return Objects.equals(content, news.content) &&
                Objects.equals(title, news.title) &&
                Objects.equals(date, news.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, title, date);
    }

    public GameDTO getGameByGameId() {
        return gameByGameId;
    }

    public void setGameByGameId(GameDTO gameByGameId) {
        this.gameByGameId = gameByGameId;
    }
}
