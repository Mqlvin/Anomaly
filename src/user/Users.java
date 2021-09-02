package user;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.Makers;
import io.Reader;
import io.Writers;

import java.io.File;
import java.util.Arrays;

public class Users {
    public static void addUser(String uuid, Integer id) {
        String userid = "";
        if(id == -1) { // Not creating a user under a pre-existing account, so create a new user ID folder.
            File ids = new File("./settings/user-settings");
            String[] idNames = ids.list();
            if(idNames.length == 0) {
                userid = "000001";
            } else {
                Arrays.sort(idNames);
                userid = String.valueOf(Integer.parseInt(idNames[idNames.length - 1]) + 1);
            }
        } else {
            userid = id.toString();
        }

        makeCache();

        File newFolder = new File("./settings/user-settings/" + userid + "/" + uuid);
        Makers.makeDir(newFolder);
        JsonObject cache = new JsonParser().parse(Reader.readJson(new File("./settings/user-settings/userid.json"))).getAsJsonObject();
        cache.add(); // TODO: ADD CACHE HERE
    }

    public static String toId(String id) { // Just a function to add the 0's on the end of the ID because I am lazy and I don't want to type it out each time.
        String newId = ("000000" + id).substring(id.length());
        if(newId.length() == 6) {
            return newId;
        }
        return null;
    }

    private static void makeCache() {
        File userToId = new File("./settings/user-settings/userid.json");
        Writers.writeFile(userToId, "{}");

        File idToUsers = new File("./settings/user-settings/players.json");
        Writers.writeFile(idToUsers, "{}");
    }
}
