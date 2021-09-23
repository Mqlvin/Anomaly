package com.anomaly.security;

import com.anomaly.console.Console;
import com.anomaly.scheduler.Handler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class KeyManager {
    public static ArrayList<HypixelKey> keys = new ArrayList<HypixelKey>();
    public static ArrayList<Integer> requests = new ArrayList<Integer>();

    public static void addCount(HypixelKey key) {

    }

    public static void startManager() {
        SimpleDateFormat sdf = new SimpleDateFormat("ss-SSS");
        String[] date = sdf.format(new Date()).split("-");
        Integer runIn = 60000 - ((Integer.parseInt(date[0]) * 1000) + Integer.parseInt(date[1]));
        // This returns the time until the start of the next minute in milliseconds, the same time the Hypixel key query limit resets.

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(0);
        Runnable run = () -> {
            if(keys.size() == requests.size()) {
                for(HypixelKey key : keys) {
                    key.setRequests(0);

                    // TODO: So the idea is, I have a method like addRequest(UUID) and add that to each request called to the API in the root of the API methods.
                }
            }
        };
        executor.scheduleAtFixedRate(run, runIn, 60000, TimeUnit.MILLISECONDS);
    }
}
