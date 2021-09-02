package user;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.Makers;
import io.Reader;
import io.Writers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Users {
    public static void createUser(String uuid, Integer id) {
        String userid = "";
        if(id == -1) { // Not creating a user under a pre-existing account, so create a new user ID folder.
            File ids = new File("./settings/user-settings");
            String[] idNames = ids.list();
            ArrayList<String> tempFiles = new ArrayList<>();
            for(String name : idNames) {
                if(name.length() == 6 && !name.contains(".")) {
                    tempFiles.add(name);
                }
            }
            idNames = tempFiles.toArray(new String[0]);
            if(idNames.length == 0) {
                userid = "000001";
            } else {
                Arrays.sort(idNames);
                userid = String.valueOf(Integer.parseInt(idNames[idNames.length - 1]) + 1);
            }
        } else {
            userid = id.toString();
        }
        userid = toId(userid);

        makeCache();

        if(new JsonParser().parse(Reader.readJson(new File("./settings/user-settings/ids.json"))).getAsJsonObject().has(uuid)) {
            return;
        }

        File newFolder = new File("./settings/user-settings/" + userid + "/" + uuid);
        Makers.makeDir(newFolder);
        JsonObject cache = new JsonParser().parse(Reader.readJson(new File("./settings/user-settings/ids.json"))).getAsJsonObject();
        cache.add(uuid, new JsonParser().parse(userid)); // Adds UUID to ID.
        JsonObject idToUUID = new JsonParser().parse(Reader.readJson(new File("./settings/user-settings/groups.json"))).getAsJsonObject();
        if(idToUUID.has(userid)) { // Adds ID to list of UUIDs.
            JsonArray players = idToUUID.getAsJsonArray(userid);
            if(!players.contains(new JsonParser().parse(uuid))) {
                players.add(uuid);
                idToUUID.remove(userid);
                idToUUID.add(userid, players);
            }
        } else {
            JsonArray players = new JsonArray();
            players.add(uuid);
            idToUUID.remove(userid);
            idToUUID.add(userid, players);
        }
        Writers.writeFile(new File("./settings/user-settings/ids.json"), cache.toString());
        Writers.writeFile(new File("./settings/user-settings/groups.json"), idToUUID.toString());

        // Cache has now been added so I need to actually work on what needs to be done.

        Makers.makeDir(new File("./settings/user-settings/" + userid));
        Makers.makeDir(new File("./settings/user-settings/" + userid + "/" + uuid));

        File userSettings = new File("./settings/user-settings/" + userid + "/" + uuid + "/settings.json");
        if(!userSettings.exists()) {
            Writers.writeFile(userSettings, Reader.readJson(new File("./settings/%default-settings.json")));
        }
    }

    public static String toId(String id) { // Just a function to add the 0's on the end of the ID because I am lazy and I don't want to type it out each time.
        String newId = ("000000" + id).substring(id.length());
        if(newId.length() == 6) {
            return newId;
        }
        return null;
    }

    private static void makeCache() {
        File userToId = new File("./settings/user-settings/ids.json"); // To convert UUID to ID.
        if(!userToId.exists()) {
            Writers.writeFile(userToId, "{}");
        }

        File idToUsers = new File("./settings/user-settings/groups.json"); // To convert ID to array of UUIDs.
        if(!idToUsers.exists()) {
            Writers.writeFile(idToUsers, "{}");
        }
    }
}
