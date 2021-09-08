package scheduler;

import api.mojang.Mojang;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.Reader;
import log.Logger;
import log.Severity;
import user.wrapper.UserAPI;

import java.io.File;

public class Scheduler {
    public UserAPI users;

    public String uuid;
    public String key;
    public Boolean doLanguageChecks;
    public Double languageCheckSensitivity;
    public JsonObject settings = new JsonObject();
    public void init(String uuidInit) {
        uuid = uuidInit;
        UserAPI users = new UserAPI();
        settings = new JsonParser().parse(Reader.readJson(new File("./settings/user-settings/" + users.getUserID(uuid) + "/" + uuid + "/settings.json"))).getAsJsonObject();

        if(settings.has("key") && !settings.get("key").toString().equalsIgnoreCase("%apiKey%")) {
            key = settings.get("key").toString();
        } else {
            Logger.log("The user " + uuid + "(" + Mojang.getUsername(uuid) + ") could not be initialized as their API key has not been set.", Severity.FATAL);
        }
        startScheduler();
    }

    private void updateSettings(String uuid) {
        settings = new JsonParser().parse(Reader.readJson(new File("./settings/user-settings/" + users.getUserID(uuid) + "/" + uuid + "/settings.json"))).getAsJsonObject();
    }

    private void startScheduler() {

    }

    private String ifHas(String key, String notValue) {
        return null;
    }
}
