package scheduler;

import checks.CheckManager;
import checks.wrapper.Checker;
import profile.SchedulerProfile;

import java.util.ArrayList;

import static java.lang.Math.floor;

public class Handler {
    public static ArrayList<SchedulerProfile> checkAccounts = new ArrayList<>();

    public static void handleChecks(Integer i) {
        for(SchedulerProfile prof : checkAccounts) {
            if(5 > prof.interval() && 300 < prof.interval()) {
                continue;
            } else if(!prof.enabled()) {
                continue;
            } else if(!prof.doLanguageChecks()) { // TODO: Always update this to ! all the checks.
                continue;
            } else if(!prof.shouldSendEmail() && !prof.shouldSendDiscordMessage()) { // TODO: Always update this value based on the new way to send warnings.
                continue;
            }
            Double tempDivide = Double.parseDouble(i.toString()) / Double.parseDouble(prof.interval().toString());
            if(floor(tempDivide) == tempDivide) {
                CheckManager.runChecks(prof);
                System.out.println("Checking: " + prof.getUsername());
            }
            /*
            Quick FYI on how this part works:
            if temdDiv rounded == tempDivide then we run the script. This means it runs on the interval the user selects.
             */
        }
    }
}
