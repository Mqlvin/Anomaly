package com.anomaly.scheduler;

import com.anomaly.checks.CheckManager;
import com.anomaly.console.CommandHandler;
import com.anomaly.console.Console;
import com.anomaly.profile.SchedulerProfile;

import java.awt.*;

import static java.lang.Math.floor;

public class CheckHandler {
    public static void handleChecks(Integer i) {
        if(CommandHandler.showCheckMessages) {
            if(Handler.checkAccounts.size() > 4) {
                Console.println("§light_greyChecking §white" + Handler.checkAccounts.get(0).getUsername() + "§light_grey, §white" + Handler.checkAccounts.get(1).getUsername() + "§light_grey and §white" + Handler.checkAccounts.get(2).getUsername() + "§light_grey and §white" + (Handler.checkAccounts.size() - 3) + " §light_greyothers.", false, Color.WHITE);
            } else if(Handler.checkAccounts.size() == 3) {
                Console.println("§light_greyChecking §white" + Handler.checkAccounts.get(0).getUsername() + "§light_grey, §white" + Handler.checkAccounts.get(1).getUsername() + "§light_grey and §white" + Handler.checkAccounts.get(2).getUsername() + "§light_grey.", false, Color.WHITE);
            } else if(Handler.checkAccounts.size() == 2) {
                Console.println("§light_greyChecking §white" + Handler.checkAccounts.get(0).getUsername() + "§light_grey and §white" + Handler.checkAccounts.get(1).getUsername() + "§light_grey.", false, Color.WHITE);
            } else if(Handler.checkAccounts.size() == 1) {
                Console.println("§light_greyChecking §white" + Handler.checkAccounts.get(0).getUsername() + "§light_grey.", false, Color.WHITE);
            } else {
                Console.println("§light_greyChecking no users, as no SchedulerProfiles were found.",false, Color.WHITE);
            }
        }
        // TODO: Fix this, it is broken (doesn't detect any SchedulerProfiles).

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
