import api.mojang.Mojang;
import backup.Backup;
import backup.BackupClearer;
import cache.hypixel.CacheDirs;
import cache.hypixel.Updater;
import http.HTTPClient;
import io.Dirs;
import io.Files;
import log.Archiver;
import log.LogClearer;
import log.Logger;
import user.Users;
import wrapper.HypixelPlayer;

import java.util.ArrayList;

public class Anomaly {
    public static final String name = "", version = "", users = "", maxUsers = "";
    public static ArrayList<String> players = new ArrayList<>();

    public static void main(String[] args) {
        Logger.initialiseLogs();
        Logger.addLogHeader();
        Archiver.zipLastLog();

        Dirs.startupDirs();
        Files.startupFiles();
        Logger.dumpAll();
        Backup.backup();

        LogClearer.removeOldLogs();
        BackupClearer.removeOldBackups();

        players.add(Mojang.getUUID("Mqlvin"));
        Users.addUser(Mojang.getUUID("Mqlvin"), -1);

        /*
        CacheDirs.makeDirs(players);
        Updater.updateCache(Mojang.getUUID("Mqlvin"), HTTPClient.requestJson("https://api.hypixel.net/player?key=d8e7f0b1-6dcf-4a96-993c-8f11cf2fe15b&uuid=73d8f852c8cb4bbb95dc63a31967e4a3"));
        HypixelPlayer player = new HypixelPlayer(Mojang.getUUID("Mqlvin"));
        System.out.println(player.getLanguage());
        System.out.println(player.getKarma());
         */
    }
}