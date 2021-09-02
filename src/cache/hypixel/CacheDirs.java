package cache.hypixel;

import io.Makers;

import java.io.File;
import java.util.ArrayList;

public class CacheDirs {
    public static void makeDirs(ArrayList<String> players) {
        for(String p : players) {
            Makers.makeDir(new File("./data/response/" + p));
        }
    }
}
