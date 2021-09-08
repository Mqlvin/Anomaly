package scheduler;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.Reader;
import user.wrapper.UserAPI;

import java.io.File;

public class Scheduler {
    public UserAPI users;

    public String uuid;
    public String key;
    public Integer interval;
    public Boolean shouldRun;

    public Boolean doLanguageChecks;
    public Double languageCheckSensitivity;
    public JsonObject settings = new JsonObject();

    public void init(String uuid_) {
        uuid = uuid_;
        updateSettings();

        startScheduler();
    }

    private void updateSettings() {
        settings = new JsonParser().parse(Reader.readJson(new File("./settings/user-settings/" + users.getUserID(uuid) + "/" + uuid + "/settings.json"))).getAsJsonObject();

        shouldRun = Handler.shouldRun(uuid);
        setIfHas(SettingType.KEY);
        setIfHas(SettingType.INTERVAL);
        setIfHas(SettingType.LANGUAGE_DO_CHECK);
        setIfHas(SettingType.LANGUAGE_CHECK_SENSITIVITY);
    }

    private void startScheduler() {

    }

    private void setIfHas(SettingType variable) {
        if(variable.equals(SettingType.KEY)) {
            if(settings.has("key") && !settings.get("key").toString().equalsIgnoreCase("%apiKey%")) {
                key = settings.get("key").toString().replace("\"", "");
            } else {
                /*
                PlayerLog.log();
                The value hasn't been set or the user hasn't been patched.
                 */
            }
        } else if(variable.equals(SettingType.LANGUAGE_DO_CHECK)) {
            if (settings.has("checkLanguage") && ifTrueOrFalse("checkLanguage")) {
                doLanguageChecks = Boolean.parseBoolean(settings.get("checkLanguage").toString().replace("\"", ""));
            } else {
                /*
                PlayerLog.log();
                Probably due to the value not being a boolean or it hasn't been properly patched.
                 */
            }
        } else if(variable.equals(SettingType.LANGUAGE_CHECK_SENSITIVITY)) {
            if (settings.has("languageSensitivity")) {
                languageCheckSensitivity = Double.parseDouble(settings.get("languageSensitivity").toString().replace("\"", ""));
            } else {
                /*
                PlayerLog.log();
                Probably due to not being properly patched.
                 */
            }
        } else if(variable.equals(SettingType.INTERVAL)) {
            if (settings.has("interval") && isSafeInteger("interval", 5, 300)) {
                interval = Integer.parseInt(settings.get("interval").toString().replace("\"", ""));
            } else {
                /*
                PlayerLog.log();
                Probably due to incorrect patching or the value for the interval is not between 5 and 300.
                 */
            }
        }
    }

    private Boolean ifTrueOrFalse(String jsonKey) {
        return settings.get(jsonKey).toString().equalsIgnoreCase("true") || settings.get(jsonKey).toString().equalsIgnoreCase("false");
    }

    private Boolean isSafeInteger(String jsonKey, Integer lowest, Integer highest) {
        return Integer.parseInt(settings.get("interval").toString().replace("\"", "")) >= lowest && Integer.parseInt(settings.get("interval").toString().replace("\"", "")) <= highest;
    }

    private Boolean isSafeDouble(String jsonKey, Double lowest, Double highest) {
        return Double.parseDouble(settings.get("interval").toString().replace("\"", "")) >= lowest && Double.parseDouble(settings.get("interval").toString().replace("\"", "")) <= highest;
    }
}