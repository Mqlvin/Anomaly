package cache.hypixel;

import io.Writers;

import java.io.File;
import java.util.Locale;

public class Updater {
    public static void updateCache(String uuid, String json) {
        Writers.writeFile(new File("./data/response/" + uuid + "/response.json"), json);
    }
}
