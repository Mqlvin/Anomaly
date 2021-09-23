package com.anomaly.scheduler;

import com.anomaly.console.CommandHandler;
import com.anomaly.console.commands.Stop;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.anomaly.console.Console;
import com.anomaly.io.Reader;
import com.anomaly.profile.SchedulerProfile;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Initialiser {
    public static void initialisePlayers() {
        Boolean hasBannedPlayers = false;
        Integer amountRegistered = 0;
        Integer amountBanned = 0;

        JsonArray arr = new JsonParser().parse(Reader.readJson(new File("./data/cache/!players.json"))).getAsJsonObject().getAsJsonArray("allPlayers");
        JsonArray banned = new JsonArray();
        if(new JsonParser().parse(Reader.readJson(new File("./settings/user-settings/banned.json"))).getAsJsonObject().has("banned")) {
            banned = new JsonParser().parse(Reader.readJson(new File("./settings/user-settings/banned.json"))).getAsJsonObject().getAsJsonArray("banned");
            hasBannedPlayers = true;
        }
        if(arr == null) {
            Console.println(" §white$ §light_greyNo players to initialise, shutting down!", false, Color.WHITE);
            Stop.stop("stop");
            return;
        }
        for(String player : new ArrayList<String>(new Gson().fromJson(arr, new TypeToken<ArrayList<String>>(){}.getType()))) {
            if(hasBannedPlayers) {
                if(banned.contains(new JsonParser().parse(player))) {
                    amountBanned += 1;
                } else {
                    Handler.checkAccounts.add(new SchedulerProfile(player));
                    amountRegistered += 1;
                }
            } else {
                Handler.checkAccounts.add(new SchedulerProfile(player));
                amountRegistered += 1;
            }
        }
        Console.println(" §white$ §light_greyInitialising §green" + amountRegistered + " §light_greyplayers, with §red" + amountBanned + " §light_greybanned.", false, Color.WHITE);
        Scheduler.startScheduler();
        Handler.enabled = true;
        Handler.shouldDisable = false;
    }
}