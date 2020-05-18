package com.steamlfg.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class ParseSteamUserData {

    public static List<String> parseUserInfoList(String stringToParse) {
        JsonParser parser = new JsonParser();
        JsonElement jsonTree = parser.parse(stringToParse);
        JsonObject obj = jsonTree.getAsJsonObject();
        System.out.println(obj);
        obj = obj.getAsJsonObject("response");
        JsonArray info = obj.getAsJsonArray("players");
        obj = info.get(0).getAsJsonObject();

        List<String> userInfo = new ArrayList<>();
        userInfo.add(obj.get("personaname").getAsString());
        userInfo.add(obj.get("avatar").getAsString());
        userInfo.add(obj.get("profileurl").getAsString());

        return userInfo;
    }
}