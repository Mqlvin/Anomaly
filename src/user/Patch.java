package user;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.Reader;
import io.Writers;
import json.GetAllKeys;
import user.wrapper.UserAPI;

import java.io.File;
import java.util.ArrayList;

public class Patch {
    public static void patchSettings(String uuid) {
        JsonObject defaultSettingsObj = new JsonParser().parse(Reader.readJson(new File("./settings/%default-settings.json"))).getAsJsonObject();

        UserAPI users = new UserAPI();
        File currentSettingsPath = new File("./settings/user-settings/" + users.getUserID(uuid) + "/" + uuid + "/settings.json");
        JsonObject currentSettings = new JsonParser().parse(Reader.readJson(currentSettingsPath)).getAsJsonObject();

        ArrayList<String> values = GetAllKeys.get(defaultSettingsObj.toString());
        System.out.println(values);

        for(String key : values) {
            if(!currentSettings.has(key)) {
                currentSettings.add(key, new JsonParser().parse(defaultSettingsObj.get(key).getAsString()));
            }
        }

        Writers.writeFile(currentSettingsPath, currentSettings.toString());
    }
}
