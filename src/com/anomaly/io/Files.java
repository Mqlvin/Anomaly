package com.anomaly.io;

import com.google.gson.JsonParser;
import com.anomaly.http.HTTPClient;
import com.anomaly.log.Logger;
import com.anomaly.log.Severity;

import java.io.File;
import java.io.IOException;

public class Files {
    public static final String[] names = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z 1 2 3 4 5 6 7 8 9 0 _".split(" ");

    public static void startupFiles() {
        try {
            if(Logger.saveLog) {
                File file = new File("./runtime/logs/latest.txt");
                file.createNewFile();
            }
        } catch(IOException e) {
            Logger.log(e.toString(), Severity.FATAL);
        }

        for(String n : names) {
            File file = new File("./data/cache/" + n + ".json");
            if(file.exists()) {
            } else {
                try {
                    file.createNewFile();
                    Writers.writeFile(file, "{}");
                } catch(IOException e) {
                    Logger.log(e.toString(), Severity.FATAL);
                }
            }
        }
        File allPlayersPath = new File("./data/cache/!players.json");
        if(!allPlayersPath.exists()) {
            Writers.writeFile(allPlayersPath, "{}");
        }
        File keys = new File("./data/keys.json");
        if(!keys.exists()) {
            Writers.writeFile(keys, "{}");
        }
        File deletedUsers = new File("./settings/user-settings/deleted.json");
        if(!deletedUsers.exists()) {
            Writers.writeFile(deletedUsers, "{}");
        }
        File bannedUsers = new File("./settings/user-settings/banned.json");
        if(!bannedUsers.exists()) {
            Writers.writeFile(bannedUsers, "{}");
        }

        String response = new JsonParser().parse(HTTPClient.requestString("https://anomaly.npkn.net/api/mail")).getAsJsonObject().get("template").toString();
        Writers.writeFile(new File("./data/%mail-template.html"), response.substring(1, response.length() - 1));
        Writers.writeFile(new File("./settings/%default-settings.json"), HTTPClient.requestJson("https://anomaly.npkn.net/api/player-settings"));
    }
}
