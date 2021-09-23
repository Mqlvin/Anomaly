package com.anomaly.user.wrapper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.anomaly.io.Reader;
import com.anomaly.io.Writers;
import com.anomaly.user.Users;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserAPI implements Wrapper {
    @Override
    public String getUserID(String uuid) {
        JsonObject obj = new JsonParser().parse(Reader.readJson(new File("./settings/user-settings/ids.json"))).getAsJsonObject();
        if(obj.has(uuid)) {
            return Users.toId(obj.get(uuid).toString().replace("\"", ""));
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
    public void initialise(String uuid, String email, String key, String interval) {
        File settingsLoc = new File("./settings/user-settings/" + getUserID(uuid) + "/" + uuid + "/settings.json");
        JsonObject obj = new JsonParser().parse(Reader.readJson(settingsLoc)).getAsJsonObject();
        setEmail(uuid, email);
        setKey(uuid, key);
        setInterval(uuid, interval);
        Writers.writeFile(settingsLoc, obj.toString());
    }

    @Override
    public void setKey(String uuid, String key) {
        File settingsLoc = new File("./settings/user-settings/" + getUserID(uuid) + "/" + uuid + "/settings.json");
        JsonObject obj = new JsonParser().parse(Reader.readJson(settingsLoc)).getAsJsonObject();
        obj.remove("key");
        obj.add("key", new JsonParser().parse(key));
        Writers.writeFile(settingsLoc, obj.toString());
    }

    @Override
    public void setEmail(String uuid, String email) {
        File settingsLoc = new File("./settings/user-settings/" + getUserID(uuid) + "/" + uuid + "/settings.json");
        JsonObject obj = new JsonParser().parse(Reader.readJson(settingsLoc)).getAsJsonObject();
        if(email.contains(".") && email.contains("@")) {
            obj.remove("email");
            obj.add("email", new JsonParser().parse(email));
            Writers.writeFile(settingsLoc, obj.toString());
        }
    }

    @Override
    public void setInterval(String uuid, String interval) {
        File settingsLoc = new File("./settings/user-settings/" + getUserID(uuid) + "/" + uuid + "/settings.json");
        JsonObject obj = new JsonParser().parse(Reader.readJson(settingsLoc)).getAsJsonObject();
        if(isSafeInteger("interval", obj, 5, 300)) {
            obj.remove("interval");
            obj.add("interval", new JsonParser().parse(interval));
            Writers.writeFile(settingsLoc, obj.toString());
        }
    }

    private Boolean isTrueOrFalse(String jsonKey, JsonObject settings) {
        return settings.get(jsonKey).toString().equalsIgnoreCase("true") || settings.get(jsonKey).toString().equalsIgnoreCase("false");
    }

    private Boolean isSafeInteger(String jsonKey, JsonObject settings, Integer lowest, Integer highest) {
        return Integer.parseInt(settings.get(jsonKey).toString().replace("\"", "")) >= lowest && Integer.parseInt(settings.get(jsonKey).toString().replace("\"", "")) <= highest;
    }

    private Boolean isSafeDouble(String jsonKey, JsonObject settings,  Double lowest, Double highest) {
        return Double.parseDouble(settings.get(jsonKey).toString().replace("\"", "")) >= lowest && Double.parseDouble(settings.get(jsonKey).toString().replace("\"", "")) <= highest;
    }
}
