package api.hypixel;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import log.Logger;
import log.Severity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static http.HTTPClient.requestJson;

public class SessionTime {
    public static String get(String uuid, String key) {
        String response = requestJson("https://api.hypixel.net/player?key=" + key + "&uuid=" + uuid);
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