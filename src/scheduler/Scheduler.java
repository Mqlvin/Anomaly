package scheduler;

import api.hypixel.Language;
import checks.LanguageChecks;
import database.LanguageDB;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    public static void initialise(String uuid, String key) {
        Runnable run = new Runnable() {
            public void run() {
                LanguageDB.addLanguage(uuid, LanguageDB.toEnum(Language.get(uuid, key)));
                LanguageChecks.checkLanguage(uuid, 0.2);
            }
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(run, 0, 15, TimeUnit.SECONDS);
    }
}

/*
    Read settings... set settings, start timer.

    In the case of a stop... stop timer, read settings, set settings, start timer.
 */