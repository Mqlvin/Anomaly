package api.mojang;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import http.HTTPClient;
import com.google.gson.JsonParser;
import io.Files;
import io.Reader;
import io.Writers;

import java.io.File;
import java.util.Map;
import java.util.Set;

public class Mojang {
    public static final JsonParser parser = new JsonParser();

    public static String getUUID(String username) {
        File file = new File("./data/cache/" + username.toUpperCase().charAt(0) + ".json");
        JsonObject cache = parser.parse(Reader.readJson(file)).getAsJsonObject();
        if(cache.has(username)) {
            return cache.get(username).toString().replace("\"", "");
        }
        String response = HTTPClient.requestJson("https://api.mojang.com/users/profiles/minecraft/" + username);
        if(!response.startsWith("{") || response == null) {
            return "Invalid username.";
        }
        JsonObject obj = parser.parse(response).getAsJsonObject();
        String UUID = obj.get("id").toString().replace("\n", "").replace("\r", "").replace("\"", "");
        File jsonFile = new File("./data/cache/" + username.charAt(0) + ".json");
        JsonObject add = new JsonParser().parse(Reader.readJson(jsonFile)).getAsJsonObject();
        if(add.has(UUID)) {
            return UUID;
        }
        add.addProperty(username, UUID);
        Writers.writeFile(jsonFile, add.toString());
        return UUID;
    }

    public static String getUsername(String UUID) {
        for(int i = 0; i < Files.names.length - 1; i++) {
            JsonObject obj = new JsonParser().parse(Reader.readJson(new File("./data/cache/" + Files.names[i] + ".json"))).getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();
            for(Map.Entry<String, JsonElement> entry : entries) {
                String[] entrySplit = entry.toString().replace("=", "").split("\"");
                if(entrySplit[1].equals(UUID)) {
                    return entrySplit[0];
                }
            }
        }
        String response = HTTPClient.requestJson("https://api.mojang.com/user/profiles/" + UUID + "/names");
        if(!response.startsWith("[") || response == null) {
            return "Invalid UUID.";
        }
        JsonArray arr = parser.parse(response).getAsJsonArray();
        JsonObject activeData = parser.parse(arr.get(arr.size() - 1).toString()).getAsJsonObject();
        String name = activeData.get("name").toString().replace("\n", "").replace("\r", "").replace("\"", "");
        File jsonFile = new File("./data/cache/" + name.charAt(0) + ".json");
        JsonObject add = new JsonParser().parse(Reader.readJson(jsonFile)).getAsJsonObject();
        if(add.has(name)) {
            return name;
        }
        add.addProperty(name, UUID);
        Writers.writeFile(jsonFile, add.toString());
        return name;
    }
}
