package oldscheduler;

import api.hypixel.Language;
import api.mojang.Mojang;
import checks.LanguageChecks;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import database.LanguageDB;
import io.Reader;
import user.wrapper.UserAPI;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    public static String key;
    public static void initialise(String uuid, Integer integer) {
        UserAPI users = new UserAPI();
        JsonObject settings = new JsonParser().parse(Reader.readJson(new File("./settings/user-settings/" + users.getUserID(uuid) + "/" + uuid + "/settings.json"))).getAsJsonObject();
        key = settings.get("key").toString();
        Runnable run = () -> {
            if(!isBanned(uuid)) {
                LanguageDB.addLanguage(uuid, LanguageDB.toEnum(Language.get(uuid, key)));
                LanguageChecks.checkLanguage(uuid, 0.2);
                System.out.println(Mojang.getUsername(uuid) + " registered.");
            }

            // TODO: add memory barrier on playerlist.

            /*
            if(Settings.changeSettings.get(integer)) {
                Settings.changeSettings.set(integer, false);

                JsonObject newSettings = new JsonParser().parse(Reader.readJson(new File("./settings/user-settings/" + users.getUserID(uuid) + "/" + uuid + "/settings.json"))).getAsJsonObject();
                key = newSettings.get("key").toString();
            }
             */
            System.out.println("4 " + Mojang.getUsername(uuid));
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(run, 0, 5, TimeUnit.SECONDS);
    }

    private static Boolean isBanned(String uuid) {
        JsonObject bannedPlayersObj = new JsonParser().parse(Reader.readJson(new File("./settings/user-settings/banned.json"))).getAsJsonObject();
        JsonArray bannedPlayers = bannedPlayersObj.getAsJsonArray("banned");
        if(bannedPlayers == null) {
            return false;
        } else return bannedPlayers.contains(new JsonParser().parse(uuid));
    }
}