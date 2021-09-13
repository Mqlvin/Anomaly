import api.mojang.Mojang;
import backup.Backup;
import backup.BackupClearer;
import io.Dirs;
import io.Files;
import log.Archiver;
import log.LogClearer;
import log.Logger;
import profile.SchedulerProfile;
import scheduler.Handler;
import scheduler.Scheduler;
import user.Patch;
import user.wrapper.UserAPI;

public class Anomaly {
    public static UserAPI users = new UserAPI();

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

        Patch.patchAllUsers();

        Handler.checkAccounts.add(new SchedulerProfile(Mojang.getUUID("Mqlvin")));
        Handler.checkAccounts.add(new SchedulerProfile(Mojang.getUUID("Veales")));
        Handler.checkAccounts.add(new SchedulerProfile(Mojang.getUUID("STiger")));
        Scheduler.startScheduler();

        /*
        SchedulerProfile prof = new SchedulerProfile(Mojang.getUUID("Mqlvin")); // Created the SchedulerProfile object.
        users.setInterval(prof.getUUID(), "10");
        prof.reloadSettings(); // Called a reload so all settings would be reloaded.
         */

        /*
        ArrayList<String> players = new ArrayList<>();
        players.add(Mojang.getUUID("Mqlvin"));
        users.setInterval(Mojang.getUUID("Mqlvin"), "5");
        for(String p : players) {
            Scheduler sch = new Scheduler();
            sch.init(p);
        }
         */

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
