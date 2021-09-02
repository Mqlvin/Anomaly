package scheduler;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import database.Entry;
import io.Reader;
import io.Writers;

import java.io.File;
import java.util.ArrayList;

public class Initialise {
    public static ArrayList<String> uuid = new ArrayList<>();
    public static String defaultSettings = Reader.readJson(new File("./data/settings/%default.json"));
    public static void init(ArrayList<String> players) {
        uuid = players;

        JsonObject keys = new JsonParser().parse(Reader.readJson(new File("./data/keys.json"))).getAsJsonObject();
        int i = 0;
        for(String p : uuid) {
            makeSettingsData(p);
            Entry.createPlayer(p);
            Settings.changeSettings.add(true);
            Settings.uuid.add(p);
            if(!keys.has(p)) {
                JsonObject cache = new JsonParser().parse(Reader.readJson(new File("./data/settings/" + p + ".json"))).getAsJsonObject();
                keys.add(p, cache.get("apiKey"));
                Writers.writeFile(new File("./data/keys.json"), keys.toString());
            }
            Scheduler.initialise(p, keys.get(p).toString().replace("\"", ""));
            i += 1;
        }
    }

    public static void makeSettingsData(String uuid) {
        File file = new File("./data/settings/" + uuid.toLowerCase() + ".json");
        if(!file.exists()) {
            Writers.writeFile(file, defaultSettings);
        }
    }
}
