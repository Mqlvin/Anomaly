package checks;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.Reader;
import user.wrapper.UserAPI;

import java.io.File;

public class CheckManager {
    private JsonObject settings;
    private Integer arrayInt;
    private File settingsPath;
    public UserAPI users = new UserAPI();

    public CheckManager(String uuid, Integer startInt) {
        arrayInt = startInt;
        settingsPath = new File("./settings/user-settings/" + users.getUserID(uuid) + "/" + uuid + "/settings.json");
        settings = new JsonParser().parse(Reader.readJson(settingsPath)).getAsJsonObject();
    }

    public static void runChecks() {

    } // TODO: Make CheckManager a wrapper so I can run checks from the each CheckManager!
}
