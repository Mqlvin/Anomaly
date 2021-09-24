package com.anomaly.api.hypixel;

import com.anomaly.security.KeyManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.anomaly.log.Logger;
import com.anomaly.log.Severity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.anomaly.http.HTTPClient.requestJson;

public class SessionTime {
    public static String get(String uuid, String key) {
        if(!KeyManager.key(uuid).shouldRequest()) {
            return null;
        }
        String response = requestJson("https://api.hypixel.net/player?key=" + key + "&uuid=" + uuid);
        KeyManager.key(uuid).addRequest();
        JsonObject obj = new JsonParser().parse(response).getAsJsonObject();
        long lastLogin = Long.parseLong(obj.getAsJsonObject("player").get("lastLogin").toString().replace("\"", "").replace("\"", ""));
        long lastLogout = Long.parseLong(obj.getAsJsonObject("player").get("lastLogout").toString().replace("\"", "").replace("\"", ""));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date lastLoginDateUnix = new Date(lastLogin * 1000L);
        Date lastLogoutDateUnix = new Date(lastLogout * 1000L);
        try {
            Date lastLoginDate = new Date(String.valueOf(sdf.parse(lastLoginDateUnix.toString())));
            Date lastLogoutDate = new Date(String.valueOf(sdf.parse(lastLogoutDateUnix.toString())));
            System.out.println(lastLogoutDate.toString());
        } catch(ParseException e) {
            Logger.log(e.toString(), Severity.FATAL);
        }
        return obj.getAsJsonObject("player").get("mcVersionRp").toString().replace("\"", "").replace("\"", "");
    }
}