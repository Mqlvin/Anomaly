package api.hypixel;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static http.HTTPClient.requestJson;

public class Status {
    public static String get(String uuid, String apiKey) {
        String response = requestJson("https://api.hypixel.net/status?key=" + apiKey + "&uuid=" + uuid);
        JsonObject obj = new JsonParser().parse(response).getAsJsonObject();

        return obj.getAsJsonObject("session").get("online").toString().replace("\"", "").replace("\"", "");
    }
}