package com.anomaly.security;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class KeyManager {
    public volatile static ArrayList<HypixelKey> keys = new ArrayList<>();

    public static void initialiseKey(String uuid, String key_) {
        if(keys.size() == 0) {
            keys.add(new HypixelKey(uuid, key_));
        } else {
            for(HypixelKey key : keys) {
                if(key.getKey().equalsIgnoreCase(key_)) {
                    key.addUUID(uuid);
                    return;
                }
            }
            keys.add(new HypixelKey(uuid, key_));
        }
        // TODO: If the array has no values it will automatically add it, I know I could run the for loop before trying to make a new key but whatever.
    }

    public static HypixelKey key(String uuid) {
        for(HypixelKey key : keys) {
            if(key.getUUIDs().contains(uuid)) {
                return key;
            }
        }
        return null;
    }

    public static void startManager() {
        SimpleDateFormat sdf = new SimpleDateFormat("ss-SSS");
        String[] date = sdf.format(new Date()).split("-");
        Integer runIn = 60000 - ((Integer.parseInt(date[0]) * 1000) + Integer.parseInt(date[1]));
        // This returns the time until the start of the next minute in milliseconds, the same time the Hypixel key query limit resets.

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(0);
        Runnable run = () -> {
            for(HypixelKey key : keys) {
                key.setRequests(0);
            }
        };
        executor.scheduleAtFixedRate(run, runIn, 1000, TimeUnit.MILLISECONDS);
    }
    // TODO: Could make this a bit better, generally runs within 2-3 ms of the time, could make it perfect however!
}
