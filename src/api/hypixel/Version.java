package api.hypixel;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static http.HTTPClient.requestJson;

public class Version {
    public static String get(String uuid, String apiKey) {
        String response = requestJson("https://api.hypixel.net/player?key=" + apiKey + "&uuid=" + uuid);
        JsonObject obj = new JsonParser().parse(response).getAsJsonObject();
        if(obj.getAsJsonObject("player").has("mcVersionRp")) {
            return obj.getAsJsonObject("player").get("mcVersionRp").toString().replace("\"", "").replace("\"", "");
        }

        return "null";
    }
}