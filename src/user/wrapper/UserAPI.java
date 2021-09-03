package user.wrapper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.Reader;
import io.Writers;
import user.Users;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserAPI implements Wrapper {
    @Override
    public String getUserID(String UUID) {
        JsonObject obj = new JsonParser().parse(Reader.readJson(new File("./settings/user-settings/ids.json"))).getAsJsonObject();
        if(obj.has(UUID)) {
            return Users.toId(obj.get(UUID).toString().replace("\"", ""));
        }
        return null;
    }

    @Override
    public ArrayList<String> getUUIDs(String ID) {
        JsonObject obj = new JsonParser().parse(Reader.readJson(new File("./settings/user-settings/groups.json"))).getAsJsonObject();
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        if(obj.has(Users.toId(ID))) {
            return new ArrayList<String>(new Gson().fromJson(obj.get(Users.toId(ID)).getAsJsonArray(), type));
        }
        return null;
    }

    @Override
    public void initialise(String UUID, String email, String key, String interval) {
        File settingsLoc = new File("./settings/user-settings/" + getUserID(UUID) + "/" + UUID + "/settings.json");
        JsonObject obj = new JsonParser().parse(Reader.readJson(settingsLoc)).getAsJsonObject();
        obj.remove("email");
        obj.add("email", new JsonParser().parse(email));
        obj.remove("key");
        obj.add("key", new JsonParser().parse(key));
        obj.remove("interval");
        obj.add("interval", new JsonParser().parse(interval));
        Writers.writeFile(settingsLoc, obj.toString());
    }

    @Override
    public void setKey(String UUID, String key) {
        File settingsLoc = new File("./settings/user-settings/" + getUserID(UUID) + "/" + UUID + "/settings.json");
        JsonObject obj = new JsonParser().parse(Reader.readJson(settingsLoc)).getAsJsonObject();
        obj.remove("key");
        obj.add("key", new JsonParser().parse(key));
        Writers.writeFile(settingsLoc, obj.toString());
    }

    @Override
    public void setEmail(String UUID, String email) {
        File settingsLoc = new File("./settings/user-settings/" + getUserID(UUID) + "/" + UUID + "/settings.json");
        JsonObject obj = new JsonParser().parse(Reader.readJson(settingsLoc)).getAsJsonObject();
        obj.remove("email");
        obj.add("email", new JsonParser().parse(email));
        Writers.writeFile(settingsLoc, obj.toString());
    }

    @Override
    public void setInterval(String UUID, String interval) {
        File settingsLoc = new File("./settings/user-settings/" + getUserID(UUID) + "/" + UUID + "/settings.json");
        JsonObject obj = new JsonParser().parse(Reader.readJson(settingsLoc)).getAsJsonObject();
        obj.remove("interval");
        obj.add("interval", new JsonParser().parse(interval));
        Writers.writeFile(settingsLoc, obj.toString());
    }
}
