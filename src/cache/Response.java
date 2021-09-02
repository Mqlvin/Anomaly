package cache;

import io.Writers;

import java.io.File;

public class Response {
    public static void cacheResponse(String json, String uuid) {
        System.out.println("writing");
        Writers.writeFile(new File("./data/playerdata/" + uuid + "/response.json"), json);
        System.out.println("written");
    }
}
