package scheduler;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import database.Entry;
import io.Reader;

import java.io.File;
import java.util.ArrayList;

public class Initialise {
    public static ArrayList<String> uuid = new ArrayList<>();
    public static void init(ArrayList<String> players) {
        uuid = players;
        int i = 0;
        for(String p : uuid) {
            Entry.createPlayer(p);
            Settings.changeSettings.add(true);
            Settings.uuid.add(p);
            Scheduler.initialise(p);
            i += 1;
        }
    }
}
