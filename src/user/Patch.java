package user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.Reader;
import io.Writers;
import json.GetAllKeys;
import user.wrapper.UserAPI;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Patch {
    public static void patchSettings(String uuid) {
        JsonObject defaultSettingsObj = new JsonParser().parse(Reader.readJson(new File("./settings/%default-settings.json"))).getAsJsonObject();

        UserAPI users = new UserAPI();
        File currentSettingsPath = new File("./settings/user-settings/" + users.getUserID(uuid) + "/" + uuid + "/settings.json");
        JsonObject currentSettings = new JsonParser().parse(Reader.readJson(currentSettingsPath)).getAsJsonObject();
        ArrayList<String> values = GetAllKeys.get(defaultSettingsObj.toString());

        for(String key : values) {
            if(!currentSettings.has(key)) {
                currentSettings.add(key, new JsonParser().parse(defaultSettingsObj.get(key).toString().replace("\"", "")));
            }
        }

        Writers.writeFile(currentSettingsPath, currentSettings.toString());
    }

    public static void patchAllUsers() {
        JsonObject playersObj = new JsonParser().parse(Reader.readJson(new File("./data/cache/!players.json"))).getAsJsonObject();
        Type arrayType = new TypeToken<ArrayList<String>>(){}.getType();
        ArrayList<String> players = new Gson().fromJson(playersObj.get("allPlayers").getAsJsonArray(), arrayType);
        for(String uuid : players) {
            patchSettings(uuid);
        }
    }
}
