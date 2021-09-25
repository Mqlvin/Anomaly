package com.anomaly.api.hypixel;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Version {
    public static String get(String response) {
        JsonObject obj = new JsonParser().parse(response).getAsJsonObject();
        if(obj.getAsJsonObject("player").has("mcVersionRp")) {
            return obj.getAsJsonObject("player").get("mcVersionRp").toString().replace("\"", "").replace("\"", "");
        }
        return null;
    }
}