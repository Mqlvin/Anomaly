package com.anomaly.api.hypixel;

import com.anomaly.security.KeyManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static com.anomaly.http.HTTPClient.requestJson;

public class Status {
    public static String get(String uuid, String key) {
        if(!KeyManager.key(uuid).shouldRequest()) {
            return null;
        }
        String response = requestJson("https://api.hypixel.net/status?key=" + key + "&uuid=" + uuid);
        KeyManager.key(uuid).addRequest();
        JsonObject obj = new JsonParser().parse(response).getAsJsonObject();
        if(obj.getAsJsonObject("session").has("online")) {
            return obj.getAsJsonObject("session").get("online").toString().replace("\"", "").replace("\"", "");
        }
        return null;
    }
}