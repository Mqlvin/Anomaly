package user.wrapper;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.Reader;
import io.Writers;

import java.io.File;

public class Infractor implements InfractorWr {
    @Override
    public void banUser(String uuid) {
        File bannedUsers = new File("./settings/user-settings/banned.json");
        JsonObject bannedUsersObj = new JsonParser().parse(Reader.readJson(bannedUsers)).getAsJsonObject();
        JsonArray bannedUsersArr = new JsonArray();
        if(bannedUsersObj.has("banned")) {
            bannedUsersArr = bannedUsersObj.getAsJsonArray("banned");
        }
        if(!bannedUsersArr.contains(new JsonParser().parse(uuid))) {
            bannedUsersArr.add(uuid);
        }
        bannedUsersObj.remove("banned");
        bannedUsersObj.add("banned", bannedUsersArr);
        Writers.writeFile(bannedUsers, bannedUsersObj.toString());
    }

    @Override
    public void unbanUser(String uuid) {
        File bannedUsers = new File("./settings/user-settings/banned.json");
        JsonObject bannedUsersObj = new JsonParser().parse(Reader.readJson(bannedUsers)).getAsJsonObject();
        JsonArray bannedUsersArr = bannedUsersObj.getAsJsonArray("banned");
        if(bannedUsersArr == null) {
            return;
        }
        if(bannedUsersArr.contains(new JsonParser().parse(uuid))) {
            bannedUsersArr.remove(new JsonParser().parse(uuid));
        }
        bannedUsersObj.remove("banned");
        bannedUsersObj.add("banned", bannedUsersArr);
        Writers.writeFile(bannedUsers, bannedUsersObj.toString());
    }
}
