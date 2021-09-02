package wrapper;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.Reader;

import java.io.File;

public class HypixelPlayer implements HypixelPlayerWr {
    private JsonObject json;

    public HypixelPlayer(String uuid) {
        json = new JsonParser().parse(Reader.readJson(new File("./data/response/" + uuid + "/response.json"))).getAsJsonObject();
    }

    @Override
    public String getLanguage() {
        return json.getAsJsonObject("player").get("userLanguage").toString().replace("\"", "");
    }

    @Override
    public String getKarma() {
        return "7";
    }
}
