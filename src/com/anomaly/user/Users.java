package com.anomaly.user;

import com.anomaly.api.mojang.Mojang;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.anomaly.io.Makers;
import com.anomaly.io.Reader;
import com.anomaly.io.Writers;
import com.anomaly.log.Logger;
import com.anomaly.log.Severity;
import com.anomaly.user.wrapper.UserAPI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Users {
    public static void createUser(String uuid, Integer id) {
        String userid = "";
        File deletedUsersPath = new File("./settings/user-settings/deleted.json");
        JsonObject deletedUsersObj = new JsonParser().parse(Reader.readJson(deletedUsersPath)).getAsJsonObject();
        if(deletedUsersObj.has(uuid)) {
            userid = deletedUsersObj.get(uuid).toString().replace("\"", "");
            deletedUsersObj.remove(uuid);
            Writers.writeFile(deletedUsersPath, deletedUsersObj.toString());
        } else {
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
        }
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

        JsonObject players = new JsonParser().parse(Reader.readJson(new File("./data/cache/!players.json"))).getAsJsonObject();
        JsonArray allPlayers = new JsonArray();
        if(players.has("allPlayers")) {
            allPlayers = players.getAsJsonArray("allPlayers");
        }
        if(!allPlayers.contains(new JsonParser().parse(uuid))) {
            allPlayers.add(uuid);
            players.remove("allPlayers");
            players.add("allPlayers", allPlayers);
            Writers.writeFile(new File("./data/cache/!players.json"), players.toString());
        }
    }

    public static void removeUser(String uuid) {
        UserAPI user = new UserAPI();
        String userid = user.getUserID(uuid);

        if(userid == null) {
            return;
        }

        File players = new File("./data/cache/!players.json");
        JsonObject playersObj = new JsonParser().parse(Reader.readJson(players)).getAsJsonObject();
        JsonArray playersArr = playersObj.getAsJsonArray("allPlayers");
        if(playersArr == null) {
            if(playersArr.contains(new JsonParser().parse(uuid))) {
                playersArr.remove(new JsonParser().parse(uuid));
            }
            playersObj.remove("allPlayers");
            if(playersArr.size() != 0) {
                playersObj.add("allPlayers", playersArr);
            }
            Writers.writeFile(players, playersObj.toString());
        }

        File groups = new File("./settings/user-settings/groups.json");
        JsonObject groupsObj = new JsonParser().parse(Reader.readJson(groups)).getAsJsonObject();
        JsonArray groupsArr = groupsObj.getAsJsonArray(userid);
        if(groupsArr != null) {
            if(groupsArr.contains(new JsonParser().parse(uuid))) {
                groupsArr.remove(new JsonParser().parse(uuid));
            }
            groupsObj.remove(userid);
            if(groupsArr.size() != 0) {
                groupsObj.add(userid, groupsArr);
            }
            Writers.writeFile(groups, groupsObj.toString());
        }

        File ids = new File("./settings/user-settings/ids.json");
        JsonObject idsObj = new JsonParser().parse(Reader.readJson(ids)).getAsJsonObject();
        if(idsObj.has(uuid)) {
            idsObj.remove(uuid);
        }
        Writers.writeFile(ids, idsObj.toString());

        File useridFolder = new File("./settings/user-settings/" + userid);
        try {
            Files.walk(Paths.get(useridFolder.toString())).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
        } catch(IOException e) {
            Logger.log(e.toString(), Severity.FATAL);
        }
        if(useridFolder.exists()) {
            useridFolder.delete();
        }
        if(useridFolder.exists()) {
            Logger.log("The user " + uuid + " (" + Mojang.getUsername(uuid) + ") was unable to be deleted.", Severity.FATAL);
        }

        File deletedUsers = new File("./settings/user-settings/deleted.json");
        JsonObject deletedUsersObj = new JsonParser().parse(Reader.readJson(deletedUsers)).getAsJsonObject();
        if(!deletedUsersObj.has(uuid)) {
            deletedUsersObj.add(uuid, new JsonParser().parse(userid));
        } else if(deletedUsersObj.get(uuid).toString() != userid) {
            deletedUsersObj.remove(uuid);
            deletedUsersObj.add(uuid, new JsonParser().parse(userid));
        }
        Writers.writeFile(deletedUsers, deletedUsersObj.toString());
    }

    public static void removeId(String id) {
        UserAPI users = new UserAPI();
        for(String user : users.getUUIDs(id)) {
            removeUser(user);
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