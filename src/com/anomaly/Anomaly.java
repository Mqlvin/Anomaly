package com.anomaly;

import com.anomaly.api.mojang.Mojang;
import com.anomaly.scheduler.Initialiser;
import com.anomaly.security.KeyManager;
import com.anomaly.startup.Start;
import com.anomaly.user.Users;
import com.anomaly.user.wrapper.UserAPI;

public class Anomaly {

    public static void main(String[] args) {
        Start.start();
        KeyManager.startManager();
        Initialiser.initialisePlayers();

        /*
        SchedulerProfile prof = new SchedulerProfile(Mojang.getUUID("Mqlvin"));
        KeyManager.initialiseKey(Mojang.getUUID("Mqlvin"), "d8e7f0b1-6dcf-4a96-993c-8f11cf2fe15b");
        Requester response = new Requester(Mojang.getUUID("Mqlvin"), RequestType.STATS);
        System.out.println(Language.get(response.data()));
        System.out.println(Version.get(response.data()));
        System.out.println(KeyManager.key(Mojang.getUUID("Mqlvin")).getRequests());
         */

        // SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        // System.out.println(sdf.format(Unix.toDate(new Long(1632167960))));

        /*
        KeyManager.initialiseKey(Mojang.getUUID("Mqlvin"),"d8e7f0b1-6dcf-4a96-993c-8f11cf2fe15b");
        KeyManager.initialiseKey(Mojang.getUUID("Veales"),"d8e7f0b1-6dcf-4a96-993c-8f11cf2fe15b");
        System.out.println(Language.get(Mojang.getUUID("Mqlvin"), KeyManager.key(Mojang.getUUID("Mqlvin")).getKey()));
        for(int i = 0; i < 65; i++) {
            KeyManager.key(Mojang.getUUID("Veales")).addRequest();
            // System.out.println(KeyManager.key(Mojang.getUUID("Veales")).getRequests() + " " + KeyManager.key(Mojang.getUUID("Veales")).shouldRequest());
        }
        System.out.println(Language.get(Mojang.getUUID("Veales"), KeyManager.key(Mojang.getUUID("Mqlvin")).getKey()));
         */

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
