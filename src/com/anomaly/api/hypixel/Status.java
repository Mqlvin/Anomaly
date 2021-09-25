package com.anomaly.api.hypixel;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Status {
    public static String get(String response) {
        JsonObject obj = new JsonParser().parse(response).getAsJsonObject();
        if(obj.getAsJsonObject("session").has("online")) {
            return obj.getAsJsonObject("session").get("online").toString().replace("\"", "").replace("\"", "");
        }
        return null;
    }
}