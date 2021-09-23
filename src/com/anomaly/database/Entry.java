package com.anomaly.database;

import com.anomaly.io.Makers;
import com.anomaly.io.Writers;

import java.io.File;

public class Entry {
    public static void createPlayer(String uuid) {
        if(!new File("./data/playerdata/" + uuid).exists()) {
            Makers.makeDir(new File("./data/playerdata/" + uuid));
        }
        if(!new File("./data/playerdata/" + uuid + "/languages.json").exists()) {
            Writers.writeFile(new File("./data/playerdata/" + uuid + "/languages.json"), "{}");
        }
    }
}