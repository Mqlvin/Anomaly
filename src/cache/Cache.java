package cache;

import api.mojang.Mojang;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.Reader;
import io.Writers;
import misc.User;

import java.io.File;

public class Cache {
    public static void addUsername(String username) {
        User user = new User(username, Mojang.getUUID(username));
        if(user.toString().contains(" ")) {
        } else {
            File file = new File("./data/cache/" + username.charAt(0) + ".json");
            JsonObject obj = new JsonParser().parse(Reader.readJson(file)).getAsJsonObject();
            obj.addProperty(username, Mojang.getUUID(username));
            Writers.writeFile(file, obj.toString());
        }
    }
}
