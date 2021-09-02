package mail;

import api.mojang.Mojang;
import io.Reader;

import java.io.File;

public class Generator {
    public static String generate(String uuid) {
        String template = Reader.readJson(new File("./data/%mail-template.html"));
        String gen = "";
        gen = template
                        .replace("%logourl%", "https://raw.githubusercontent.com/Mqlvin/mqlvin.github.io/main/anomaly/logo.png")
                        .replace("%websiteurl%", "mqlvin.github.io") // - Not finished.
                        .replace("%username%", Mojang.getUsername(uuid))
                        .replace("%uuid%", uuid)
                        .replace("%language%", "language") // - Get language.
                        .replace("%version%", "version")
                        .replace("%timezone%", "timezone")
                        .replace("%microsoftloginchange%", "https://www.minecraft.net/en-us/login")
                        .replace("%migrationlink%", "https://help.minecraft.net/hc/en-us/articles/360050865492-Minecraft-Java-Edition-Account-Migration-FAQ")
                        .replace("%supportlink%", "https://support.hypixel.net/hc/en-us/requests/new")
                        .replace("%hypixelsupportlink%", "timezone")
                        .replace("%preferencesurl%", "mqlvin.github.io")
                        .replace("%preferenceslink%", "mqlvin.github.io/preferences");
        return gen;
    }
}
