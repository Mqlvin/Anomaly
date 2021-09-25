package com.anomaly.profile;

import java.util.ArrayList;

public class ProfileManager {
    public static ArrayList<SchedulerProfile> profiles = new ArrayList<>();

    public static SchedulerProfile profile(String uuid) {
        if(profiles.size() != 0) {
            for(SchedulerProfile profile : profiles) {
                if(profile.getUUID().equalsIgnoreCase(uuid)) {
                    return profile;
                }
            }
        }
        return null;
    }

    public static Boolean isCreated(String uuid) {
        if(profiles.size() != 0) {
            for(SchedulerProfile profile : profiles) {
                if(profile.getUUID().equalsIgnoreCase(uuid)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
