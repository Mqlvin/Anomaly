package user.wrapper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.Reader;
import user.Users;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserAPI implements Wrapper {
    @Override
    public String getUserID(String UUID) {
        JsonObject obj = new JsonParser().parse(Reader.readJson(new File("./settings/user-settings/ids.json"))).getAsJsonObject();
        if(obj.has(UUID)) {
            return obj.get(UUID).toString().replace("\"", "");
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
}
