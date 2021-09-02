package stats;

import io.Writers;

import java.io.File;

public class StatManager {
    public static void createStats(String uuid) {
        File file = new File("./data/playerdata/" + uuid + "/stats.json");
        if(!file.exists()) {
            Writers.writeFile(file, "{}");
        }
    }
}
