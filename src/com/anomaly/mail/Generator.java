package com.anomaly.mail;

import com.anomaly.api.mojang.Mojang;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.anomaly.io.Reader;
import com.anomaly.user.wrapper.UserAPI;

import java.io.File;

public class Generator {
    public static String generate(String uuid) {
        UserAPI users = new UserAPI();
        String template = Reader.readJson(new File("./data/%mail-template.html"));
        JsonObject latestResponse = new JsonParser().parse(Reader.readJson(new File("./data/response/" + uuid + "/response.json"))).getAsJsonObject();
        JsonObject userSettings = new JsonParser().parse(Reader.readJson(new File("./settings/user-settings/" + users.getUserID(uuid) + "/" + uuid + "/settings.json"))).getAsJsonObject();
        String gen = "";
        gen = template
                .replace("%logourl%", "https://raw.githubusercontent.com/Mqlvin/mqlvin.github.io/main/anomaly/logo.png")
                .replace("%websiteurl%", "mqlvin.github.io") // - Not finished.
                .replace("%username%", Mojang.getUsername(uuid))
                .replace("%uuid%", uuid)
                .replace("%language%", latestResponse.getAsJsonObject("player").get("userLanguage").toString().replace("\"", "")) // - Get language.
                .replace("%version%", latestResponse.getAsJsonObject("player").get("mcVersionRp").toString().replace("\"", ""))
                .replace("%timezone%", userSettings.get("timezone").toString().replace("\"", ""))
                .replace("%microsoftloginchange%", "https://www.minecraft.net/en-us/login")
                .replace("%migrationlink%", "https://help.minecraft.net/hc/en-us/articles/360050865492-Minecraft-Java-Edition-Account-Migration-FAQ")
                .replace("%supportlink%", "https://support.hypixel.net/hc/en-us/requests/new")
                .replace("%hypixelsupportlink%", "timezone")
                .replace("%preferencesurl%", "mqlvin.github.io")
                .replace("%preferenceslink%", "mqlvin.github.io/preferences");
        return gen;
    }

    /*
    Note to self:
    Always add HTTPS to start of all URLs, thanks!
     */
}
