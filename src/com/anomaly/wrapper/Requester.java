package com.anomaly.wrapper;

import com.anomaly.api.mojang.Mojang;
import com.anomaly.console.Console;
import com.anomaly.profile.ProfileManager;
import com.anomaly.profile.SchedulerProfile;
import com.anomaly.scheduler.Handler;
import com.anomaly.security.KeyManager;

import java.awt.*;
import java.security.Key;

import static com.anomaly.http.HTTPClient.requestJson;

public class Requester implements RequesterInterface {
    private String response = null;

    public Requester(String uuid, RequestType type) {
        SchedulerProfile profile = ProfileManager.profile(uuid);
        if(!KeyManager.key(uuid).shouldRequest() || !ProfileManager.isCreated(uuid)) {
            Console.println("Unable to make an API request for " + Mojang.getUsername(uuid) + ", the request count was " + KeyManager.key(uuid).getRequests(), false, Color.RED);
            return;
        } else {
            if(profile.getKey() == null) {
                return;
            }
            if(type.equals(RequestType.STATS)) {
                response = requestJson("https://api.hypixel.net/player?key=" + profile.getKey() + "&uuid=" + uuid);
            } else if(type.equals(RequestType.STATUS)) {
                response = requestJson("https://api.hypixel.net/status?key=" + profile.getKey() + "&uuid=" + uuid);
            } else if(type.equals(RequestType.RECENT_GAMES)) {
                response = requestJson("https://api.hypixel.net/recentgames?key=" + profile.getKey() + "&uuid=" + uuid);
            } else if(type.equals(RequestType.WATCHDOG)) {
                response = requestJson("https://api.hypixel.net/punishmentstats?key=" + profile.getKey());
            }
            KeyManager.key(uuid).addRequest();
        }
    }

    @Override
    public String data() {
        return response;
    }
}
