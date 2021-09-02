package json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class GetAllKeys {
    public static ArrayList<String> get(JsonObject obj) {
        ArrayList<String> keys = new ArrayList<>();
        Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();
        for(Map.Entry<String, JsonElement> entry : entries) {
            keys.add(entry.getKey());
        }
        return keys;
    }

    public static ArrayList<String> get(String json) {
        ArrayList<String> keys = new ArrayList<>();
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();
        for(Map.Entry<String, JsonElement> entry : entries) {
            keys.add(entry.getKey());
        }
        return keys;
    }
}
