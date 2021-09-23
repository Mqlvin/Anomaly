package com.anomaly.stats;

import com.anomaly.io.Writers;

import java.io.File;

public class StatManager {
    public static void createStats(String uuid) {
        File file = new File("./data/playerdata/" + uuid + "/stats.json");
        if(!file.exists()) {
            Writers.writeFile(file, "{}");
        }
    }
}