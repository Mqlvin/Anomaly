package scheduler;

import api.hypixel.Language;
import checks.LanguageChecks;
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
    public static void initialise(String uuid) {
        UserAPI users = new UserAPI();
        JsonObject settings = new JsonParser().parse(Reader.readJson(new File("./settings/user-settings/" + users.getUserID(uuid) + "/" + uuid + "/settings.json"))).getAsJsonObject();

        String key = settings.get("key").toString();
        Runnable run = () -> {
            LanguageDB.addLanguage(uuid, LanguageDB.toEnum(Language.get(uuid, key)));
            LanguageChecks.checkLanguage(uuid, 0.2);
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(run, 0, 15, TimeUnit.SECONDS);
    }
}

/*
    Read settings... set settings, start timer.

    In the case of a stop... stop timer, read settings, set settings, start timer.

    JsonObject bannedObj = new JsonParser().parse(Reader.readJson(new File("./settings/user-settings/banned.json"))).getAsJsonObject();
    JsonArray bannedArr = bannedObj.getAsJsonArray("banned");

    ALSO NEED TO BLOCK BANNED USERS BUT CHECK FOR UNBAN ON THE RUNNABLE!
 */