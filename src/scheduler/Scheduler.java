package scheduler;

import checks.wrapper.Checker;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.Reader;
import log.Severity;
import mail.Sender;
import playerlog.PlayerLog;
import user.wrapper.UserAPI;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    public UserAPI users = new UserAPI();
    public Checker checker;
    public File settingsPath;

    public String uuid;
    public String key;
    public Integer interval;
    public Boolean shouldRun;

    public Boolean doLanguageChecks;
    public Double languageCheckSensitivity;
    public JsonObject settings;

    public void init(String uuid_) {
        uuid = uuid_;
        settingsPath = new File("./settings/user-settings/" + users.getUserID(uuid) + "/" + uuid + "/settings.json");

        Handler.registerPlayer(uuid);
        updateSettings();
        startScheduler(interval);
        System.out.println("hello i made it here");
    }

    public void updateSettings() {
        if(settingsPath.exists()) {
            settings = new JsonParser().parse(Reader.readJson(settingsPath)).getAsJsonObject();
        } else {
            PlayerLog.log("No player settings were found upon request. The scheduler has been cancelled.", uuid, Severity.FATAL);
            Handler.setRunning(uuid, false);
        }
        /*
        shouldRun = Handler.shouldRun(uuid);
        setIfHas(SettingType.KEY);
        setIfHas(SettingType.INTERVAL);
        setIfHas(SettingType.LANGUAGE_DO_CHECK);
        setIfHas(SettingType.LANGUAGE_CHECK_SENSITIVITY);
         */
    }

    public void startScheduler(Integer interval) {
        System.out.println(Handler.shouldRun(uuid));
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(0);
        Runnable run = () -> {
            if(Handler.shouldRun(uuid)) {
                System.out.println("running checks");
            } else {
                try {
                    if(!Handler.shouldChangeSettings(uuid)) {
                        System.out.println("change settings? " + Handler.shouldChangeSettings(uuid));
                        executor.shutdown();
                        updateSettings();
                    }
                } catch(Exception e) {
                    System.out.println("ex");
                }
            }
        };
        executor.scheduleAtFixedRate(run, 0, interval, TimeUnit.SECONDS);

        /*
        TODO: Implement a ScheduledExecutorService class extension.
        Okay so we make a new class with the ScheduledExecutorService, and then add functions to and stop, start e.g. all logic is hosted in the main Scheduler class, the
        ScheduledExecutorService is basically just an extension to it, making it easier to run for me.
         */
    }

    /*
    public void checkForRun() {
        if(Handler.shouldRun(uuid)) {
            System.out.println("running checks");
        } else {
            executor.shutdown();
            updateSettings();
            startScheduler(interval);
        }
    }
     */

    /*
    If you are looking at this project on GitHub, this next method does all the checks, except it just calls more methods lol.
    It also handles the responses of the checks and runs more methods to do the appropriate warning.
     */

    public void runChecks() {
        if(checker.checkLanguage(uuid, languageCheckSensitivity)) {
            Sender.sendEmail(uuid);
        }
    }
    /*
    public void setIfHas(SettingType variable) {
        if(variable.equals(SettingType.KEY)) {
            if(settings.has("key") && !settings.get("key").toString().equalsIgnoreCase("%apiKey%")) {
                key = settings.get("key").toString().replace("\"", "");
            } else {
                PlayerLog.log("The scheduler started however the users API key hasn't been initialized or the value does not exist, current value (" + settings.get("key").toString() + ").", uuid, Severity.FATAL);
            }
        } else if(variable.equals(SettingType.LANGUAGE_DO_CHECK)) {
            if(settings.has("checkLanguage") && isTrueOrFalse("checkLanguage")) {
                doLanguageChecks = Boolean.parseBoolean(settings.get("checkLanguage").toString().replace("\"", ""));
            } else {
                PlayerLog.log("The requested value of LANGUAGE_DO_CHECK (" + settings.get("checkLanguage").toString() + ") was either not a valid boolean, or doesn't exist.", uuid, Severity.FATAL);
            }
        } else if(variable.equals(SettingType.LANGUAGE_CHECK_SENSITIVITY)) {
            if(settings.has("languageSensitivity") && isSafeDouble("languageSensitivity", 0.01, 0.99)) {
                languageCheckSensitivity = Double.parseDouble(settings.get("languageSensitivity").toString().replace("\"", ""));
            } else {
                PlayerLog.log("The requested value of LANGUAGE_CHECK_SENSITIVITY (" + settings.get("languageSensitivity").toString() + ") was either not a valid double (0.01 -> 0.99) or doesn't exist.", uuid, Severity.FATAL);
            }
        } else if(variable.equals(SettingType.INTERVAL)) {
            if(settings.has("interval") && isSafeInteger("interval", 5, 300)) {
                interval = Integer.parseInt(settings.get("interval").toString().replace("\"", ""));
            } else {
                PlayerLog.log("The requested value for the scheduler interval (" + settings.get("interval").toString() + ") exceeded the bounds (5 -> 300).", uuid, Severity.FATAL);
            }
        }
    }
     */



    // TODO: Could move this to the math package but idm.
}