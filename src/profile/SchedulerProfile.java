package profile;

import api.mojang.Mojang;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.Reader;
import log.Severity;
import playerlog.PlayerLog;
import user.Users;
import user.wrapper.UserAPI;

import java.io.File;

public class SchedulerProfile implements CheckProfileInterface {
    public UserAPI users = new UserAPI();

    private File settingsPath;
    private JsonObject settings;

    private Integer interval = 120;
    private Boolean enabled = false;
    private String username;
    private String uuid;

    private String email;
    private String discordID;
    private String key;
    private String timezone;

    private Boolean shouldSendEmail;
    private Boolean shouldSendDiscordMessage;

    private Boolean doLanguageChecks;
    private Double languageSensitivity;

    private Boolean attemptedReload = false;

    public SchedulerProfile(String uuid_) {
        settingsPath = new File("./settings/user-settings/" + users.getUserID(uuid_) + "/" + uuid_ + "/settings.json");
        uuid = uuid_;
        reloadSettings();
    }

    @Override
    public void reloadSettings() {
        if(!settingsPath.exists()) {
            if(attemptedReload) {
                PlayerLog.log("The user settings for " + uuid + " (" + Mojang.getUsername(uuid) + ") could not be located.", uuid, Severity.FATAL);
                return;
            }
            UserAPI users = new UserAPI();
            if(users.getUserID(uuid) != null) {
                Users.createUser(uuid, Integer.parseInt(users.getUserID(uuid)));
            } else {
                Users.createUser(uuid, -1);
            }
            enabled = false;
            attemptedReload = true;
            reloadSettings();
        }
        JsonObject settingsTemp = new JsonParser().parse(Reader.readJson(new File("./settings/user-settings/" + users.getUserID(uuid) + "/" + uuid + "/settings.json"))).getAsJsonObject();
        if(settingsTemp == null) {
            PlayerLog.log("The user settings for " + uuid + " (" + Mojang.getUsername(uuid) + ") were not found, as the file contents were corrupted or missing.", uuid, Severity.FATAL);
            enabled = false;
            return;
        }
        settings = settingsTemp;

        if(Mojang.getUsername(uuid) != null) {
            username = Mojang.getUsername(uuid);
            // For anyone or myself concerned by getting the value twice, it will only ever make one request.
        } else {
            PlayerLog.log("The players username could not be set. " + uuid + " (" + Mojang.getUsername(uuid) + ").", uuid, Severity.FATAL);
            // LOL I mean if it can't set the username variable I would be surprised if it would be able to log this message, and with the correct UUID/username but why not :smirk:
        }
        if(settings.has("interval") && isSafeInteger("interval", 5, 300)) {
            interval = Integer.parseInt(settings.get("interval").toString().replace("\"", ""));
            PlayerLog.log("The \"interval\" has been set to " + uuid + " (" + Mojang.getUsername(uuid) + ")!", uuid, Severity.INFO);
        } else {
            PlayerLog.log("The \"interval\" key wasn't found in " + uuid + " (" + Mojang.getUsername(uuid) + ")'s settings file.", uuid, Severity.FATAL);
        }
        if(settings.has("enabled") && isTrueOrFalse("enabled")) {
            enabled = Boolean.parseBoolean(settings.get("enabled").toString().replace("\"", ""));
        } else {
            PlayerLog.log("The \"enabled\" key wasn't found in " + uuid + " (" + Mojang.getUsername(uuid) + ")'s settings file.", uuid, Severity.FATAL);
        }
        if(settings.has("key") && !settings.get("key").toString().equalsIgnoreCase("%apiKey%")) {
            key = settings.get("key").toString().replace("\"", "");
        } else {
            PlayerLog.log("The \"key\" key wasn't found in " + uuid + " (" + Mojang.getUsername(uuid) + ")'s settings file.", uuid, Severity.FATAL);
        }
        if(settings.has("discordID") && settings.get("discordID").toString().equalsIgnoreCase("%discordID%")) {
            discordID = settings.get("discordID").toString().replace("\"", "");
            // TODO: Add Discord ID support.
        } else {
            PlayerLog.log("The \"discordID\" key wasn't found in " + uuid + " (" + Mojang.getUsername(uuid) + ")'s settings file.", uuid, Severity.FATAL);
        }
        if(settings.has("email") && settings.get("email").toString().contains("@") && settings.get("email").toString().contains(".") && !settings.get("email").toString().equalsIgnoreCase("%email%")) {
            email = settings.get("email").toString().replace("\"", "");
        } else {
            PlayerLog.log("The \"email\" key wasn't found in " + uuid + " (" + Mojang.getUsername(uuid) + ")'s settings file.", uuid, Severity.FATAL);
        }
        if(settings.has("timezone")) {
            timezone = settings.get("timezone").toString().replace("\"", "");
        } else {
            PlayerLog.log("The \"timezone\" key wasn't found in " + uuid + " (" + Mojang.getUsername(uuid) + ")'s settings file.", uuid, Severity.FATAL);
        }

        if(settings.has("sendEmail") && isTrueOrFalse("sendEmail")) {
            shouldSendEmail = Boolean.parseBoolean(settings.get("sendEmail").toString().replace("\"", ""));
        } else {
            PlayerLog.log("The \"sendEmail\" key wasn't found in " + uuid + " (" + Mojang.getUsername(uuid) + ")'s settings file.", uuid, Severity.FATAL);
        }
        if(settings.has("sendDiscordMessage") && isTrueOrFalse("sendDiscordMessage")) {
            shouldSendDiscordMessage = Boolean.parseBoolean(settings.get("sendDiscordMessage").toString().replace("\"", ""));
        } else {
            PlayerLog.log("The \"sendDiscordMessage\" key wasn't found in " + uuid + " (" + Mojang.getUsername(uuid) + ")'s settings file.", uuid, Severity.FATAL);
        }

        if(settings.has("checkLanguage") && isTrueOrFalse("checkLanguage")) {
            doLanguageChecks = Boolean.parseBoolean(settings.get("checkLanguage").toString().replace("\"", ""));
        } else {
            PlayerLog.log("The \"checkLanguage\" key wasn't found in " + uuid + " (" + Mojang.getUsername(uuid) + ")'s settings file.", uuid, Severity.FATAL);
        }
        if(settings.has("languageSensitivity") && isSafeDouble("languageSensitivity", 0.01, 0.99)) {
            languageSensitivity = Double.parseDouble(settings.get("languageSensitivity").toString().replace("\"", ""));
        } else {
            PlayerLog.log("The \"languageSensitivity\" key wasn't found in " + uuid + " (" + Mojang.getUsername(uuid) + ")'s settings file.", uuid, Severity.FATAL);
        }
    }

    @Override
    public Integer interval() {
        if(interval != null) {
            return interval;
        }
        return null;
    }

    @Override
    public Boolean enabled() {
        if(enabled != null) {
            return enabled;
        }
        return null;
    }

    @Override
    public String getUsername() {
        if(username != null) {
            return username;
        }
        return null;
    }

    @Override
    public String getUUID() {
        if(uuid != null) {
            return uuid;
        }
        return null;
    }

    @Override
    public String getEmail() {
        if(email != null) {
            return email;
        }
        return null;
    }

    @Override
    public String getDiscordID() {
        if(discordID != null) {
            return discordID;
        }
        return null;
    }

    @Override
    public String getKey() {
        if(key != null) {
            return key;
        }
        return null;
    }

    @Override
    public String getTimezone() {
        if(timezone != null) {
            return timezone;
        }
        return null;
    }

    @Override
    public Boolean shouldSendEmail() {
        if(shouldSendEmail != null) {
            return shouldSendEmail;
        }
        return null;
    }

    @Override
    public Boolean shouldSendDiscordMessage() {
        if(shouldSendDiscordMessage != null) {
            return shouldSendDiscordMessage;
        }
        return null;
    }

    @Override
    public Boolean doLanguageChecks() {
        if(doLanguageChecks != null) {
            return doLanguageChecks;
        }
        return null;
    }

    @Override
    public Double getLanguageSensitivity() {
        if(languageSensitivity != null) {
            return languageSensitivity;
        }
        return null;
    }

    private Boolean isTrueOrFalse(String jsonKey) {
        return settings.get(jsonKey).toString().replace("\"", "").equalsIgnoreCase("true") || settings.get(jsonKey).toString().replace("\"", "").equalsIgnoreCase("false");
    }

    private Boolean isSafeInteger(String jsonKey, Integer lowest, Integer highest) {
        return Integer.parseInt(settings.get(jsonKey).toString().replace("\"", "")) >= lowest && Integer.parseInt(settings.get(jsonKey).toString().replace("\"", "")) <= highest;
    }

    private Boolean isSafeDouble(String jsonKey, Double lowest, Double highest) {
        return Double.parseDouble(settings.get(jsonKey).toString().replace("\"", "")) >= lowest && Double.parseDouble(settings.get(jsonKey).toString().replace("\"", "")) <= highest;
    }
}