package com.anomaly.scheduler;

import com.anomaly.checks.CheckManager;
import com.anomaly.profile.SchedulerProfile;

import static java.lang.Math.floor;

public class CheckHandler {
    public static void handleChecks(Integer i) {
        for(SchedulerProfile prof : Handler.checkAccounts) {
            if(5 > prof.interval() && 300 < prof.interval()) {
                continue;
            } else if(!prof.enabled()) {
                continue;
            } else if(!prof.doLanguageChecks()) { // TODO: Always update this to ! all the checks.
                continue;
            } else if(!prof.shouldSendEmail() && !prof.shouldSendDiscordMessage()) { // TODO: Always update this value based on the new way to send warnings.
                continue;
            } else if(prof.getKey().equalsIgnoreCase("%apiKey%")) {
                continue;
            }
            Double tempDivide = Double.parseDouble(i.toString()) / Double.parseDouble(prof.interval().toString());
            if(floor(tempDivide) == tempDivide) {
                new Thread(() -> {
                    CheckManager.runChecks(prof);
                }).start();
                System.out.println("Checking: " + prof.getUsername());
            }
            /*
            Quick FYI on how this part works:
            if temdDiv rounded == tempDivide then we run the script. This means it runs on the interval the user selects.
            Really this is running:
            if currentInt / your interval
            currentInt updates by 5 every 5 seconds.
             */
        }
    }
}
