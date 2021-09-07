package scheduler;

import api.mojang.Mojang;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import database.Entry;
import io.Reader;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Initialise {
    public static ArrayList<String> uuid = new ArrayList<>();
    public static ArrayList<String> banned = new ArrayList<>();
    public static void init() {
        JsonObject allPlayersObj = new JsonParser().parse(Reader.readJson(new File("./data/cache/!players.json"))).getAsJsonObject();
        JsonArray allPlayers = allPlayersObj.getAsJsonArray("allPlayers");
        System.out.println(allPlayers);
        Type arrayType = new TypeToken<ArrayList<String>>(){}.getType();
        uuid = new Gson().fromJson(allPlayers, arrayType);
        System.out.println(uuid);
        JsonObject bannedPlayersObj = new JsonParser().parse(Reader.readJson(new File("./settings/user-settings/banned.json"))).getAsJsonObject();
        JsonArray bannedPlayers = bannedPlayersObj.getAsJsonArray("banned");
        banned = new Gson().fromJson(bannedPlayers, arrayType);
        int i = 0;
         for(String p : uuid) {
            Entry.createPlayer(p);
            Settings.changeSettings.add(true);
            if(banned == null) {
                Settings.isRunning.add(true);
            } else {
                Settings.isRunning.add(!banned.contains(p));
            }
            Settings.uuids.add(p);
            Scheduler.initialise(p, i);
            i += 1;
        }
    }
}
