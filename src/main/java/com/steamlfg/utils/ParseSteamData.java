package com.steamlfg.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.steamlfg.model.dto.GameDTO;
import com.steamlfg.model.entity.Game;
import com.steamlfg.model.entity.News;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ParseSteamData {

    public static List<String> parseUserInfoList(String stringToParse) {
        JsonParser parser = new JsonParser();
        JsonElement jsonTree = parser.parse(stringToParse);
        JsonObject obj = jsonTree.getAsJsonObject();
        obj = obj.getAsJsonObject("response");
        JsonArray info = obj.getAsJsonArray("players");
        obj = info.get(0).getAsJsonObject();

        List<String> userInfo = new ArrayList<>();
        userInfo.add(obj.get("personaname").getAsString());
        userInfo.add(obj.get("avatar").getAsString());
        userInfo.add(obj.get("profileurl").getAsString());

        return userInfo;
    }

    public static List<Game> parseGameList(String stringToParse) {
        JsonParser parser = new JsonParser();
        List<Game> gameList = new ArrayList();
        JsonElement jsonTree = parser.parse(stringToParse);
        JsonObject obj = jsonTree.getAsJsonObject();
        obj = obj.getAsJsonObject("applist");
        JsonElement apps = obj.get("apps");

        Iterator<JsonElement> iterator = apps.getAsJsonObject().get("app").getAsJsonArray().iterator();

        while (iterator.hasNext()) {
            JsonElement element = iterator.next();
            Game game = new Game();
            game.setGameName(element.getAsJsonObject().get("name").getAsString());
            game.setSteamAppId(element.getAsJsonObject().get("appid").getAsString());
            gameList.add(game);
        }
        return gameList;
    }

    public static List<News> parseNewsList(String stringToParse){
        JsonParser parser = new JsonParser();
        List<News> newsList = new ArrayList();
        JsonElement jsonTree = parser.parse(stringToParse);
        JsonObject obj = jsonTree.getAsJsonObject();
        obj = obj.getAsJsonObject("appnews");
        JsonElement apps = obj.get("newsitems");

        Iterator<JsonElement> iterator = apps.getAsJsonObject().get("newsitem").getAsJsonArray().iterator();
        while (iterator.hasNext()) {
            JsonElement element = iterator.next();
            News news = new News();
            news.setContent(element.getAsJsonObject().get("contents").getAsString());
            news.setTitle(element.getAsJsonObject().get("title").toString());
            Long epoch = Long.parseLong(element.getAsJsonObject().get("date").toString())*1000;
            news.setDate(new Timestamp(new Date(epoch).getTime()));
            newsList.add(news);
        }

        return newsList;
    }
}