package com.anomaly.api.hypixel;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Language {
    public static String get(String response) {
        JsonObject obj = new JsonParser().parse(response).getAsJsonObject();
        if(obj.getAsJsonObject("player").has("userLanguage")) {
            return obj.getAsJsonObject("player").get("userLanguage").toString().replace("\"", "").replace("\"", "");
        }
        return null;
    }
}