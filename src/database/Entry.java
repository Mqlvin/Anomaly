package database;

import io.Makers;
import io.Writers;

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
