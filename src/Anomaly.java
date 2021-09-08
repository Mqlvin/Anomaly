import api.mojang.Mojang;
import backup.Backup;
import backup.BackupClearer;
import console.gui.GUI;
import io.Dirs;
import io.Files;
import log.Archiver;
import log.LogClearer;
import log.Logger;
import schedulerrr.Initialise;
import user.Patch;
import user.wrapper.Infractor;

public class Anomaly {
    public static final String name = "", version = "", users = "", maxUsers = "";

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

        System.out.println(Boolean.parseBoolean("true"));
        System.out.println(Boolean.parseBoolean("false"));
        System.out.println(Boolean.parseBoolean("1"));
        System.out.println(Boolean.parseBoolean("0"));
        System.out.println(Boolean.parseBoolean("yes"));
        System.out.println(Boolean.parseBoolean("no"));

        // GUI.createFrame();

        /*
        Infractor infractor = new Infractor();
        infractor.banUser(Mojang.getUUID("ToggleOnU"));
        infractor.banUser(Mojang.getUUID("STiger"));
        infractor.banUser(Mojang.getUUID("Veales"));

        Patch.patchAllUsers();
        Initialise.init();
         */

        /*
        Checker checker = new Checker();
        System.out.println(checker.checkLanguage(Mojang.getUUID("Mqlvin"), 0.1));
        System.out.println(checker.checkLanguage(Mojang.getUUID("Mqlvin"), 0.9));
        */

        /*
        Infractor infractor = new Infractor();
        infractor.unbanUser(Mojang.getUUID("Subscwibe"));
        infractor.unbanUser(Mojang.getUUID("STiger"));
        */

        /*
        UserAPI users = new UserAPI();
        Users.createUser(Mojang.getUUID("Mqlvin"), -1);
        Users.createUser(Mojang.getUUID("STiger"), -1);
        Users.createUser(Mojang.getUUID("Veales"), -1);
        Users.createUser(Mojang.getUUID("Subscwibe"), Integer.parseInt(users.getUserID(Mojang.getUUID("Veales"))));
        Users.createUser(Mojang.getUUID("ToggleOnU"), -1);
        Patch.patchAllUsers();
         */

        /*
        Users.createUser(Mojang.getUUID("Veales"), -1);
        UserAPI users = new UserAPI();
        users.setKey(Mojang.getUUID("Veales"), "abc123");
        users.setInterval(Mojang.getUUID("Subscwibe"), "30");
         */

        /*
        UserAPI users = new UserAPI();
        System.out.println(users.getUserID(Mojang.getUUID("Veales")));
        ArrayList<String> newPlayers = users.getUUIDs("2");
        System.out.println(newPlayers.toString());
         */

        /*
        CacheDirs.makeDirs(players);
        Updater.updateCache(Mojang.getUUID("Mqlvin"), HTTPClient.requestJson("https://api.hypixel.net/player?key=d8e7f0b1-6dcf-4a96-993c-8f11cf2fe15b&uuid=73d8f852c8cb4bbb95dc63a31967e4a3"));
        HypixelPlayer player = new HypixelPlayer(Mojang.getUUID("Mqlvin"));
        System.out.println(player.getLanguage());
        System.out.println(player.getKarma());
         */
    }
}
