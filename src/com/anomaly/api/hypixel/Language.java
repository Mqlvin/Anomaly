package com.anomaly.api.hypixel;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static com.anomaly.http.HTTPClient.requestJson;

public class Language {
    public static String get(String uuid, String key) {
        String response = requestJson("https://api.hypixel.net/player?key=" + key + "&uuid=" + uuid);
        JsonObject obj = new JsonParser().parse(response).getAsJsonObject();
        if(obj.getAsJsonObject("player").has("userLanguage")) {
            return obj.getAsJsonObject("player").get("userLanguage").toString().replace("\"", "").replace("\"", "");
        }
        return null;
    }
}