package io;

import http.HTTPClient;
import log.Logger;
import log.Severity;

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
        File keys = new File("./data/keys.json");
        if(!keys.exists()) {
            Writers.writeFile(keys, "{}");
        }

        Writers.writeFile(new File("./data/settings/%default.json"), HTTPClient.requestJson("https://mqlvin.github.io/api/player-settings"));
        Writers.writeFile(new File("./data/%mail-template.html"), HTTPClient.requestString("https://mqlvin.github.io/api/mail-template.txt"));
    }
}
