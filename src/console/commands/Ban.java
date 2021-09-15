package console.commands;

import api.mojang.Mojang;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import console.Console;
import io.Reader;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Ban {
    public static void ban(String command) {
        if(command.toLowerCase().startsWith("ban")) {
            if(command.split(" ").length == 2 || command.split(" ").length == 3) {
                if(command.split(" ")[1].equalsIgnoreCase("list")) {
                    if(command.split(" ").length == 2) {
                        Console.println("§redInvalid use of command §light_greyban§red. Try §light_greyban list (usernames/uuids)§red.", false, Color.RED);
                        return;
                    }
                    if(command.split(" ")[2].equalsIgnoreCase("uuid") || command.split(" ")[2].equalsIgnoreCase("uuids")) {
                        JsonArray banned = new JsonParser().parse(Reader.readJson(new File("./settings/user-settings/banned.json"))).getAsJsonObject().getAsJsonArray("banned");
                        StringBuilder list = new StringBuilder();
                        if(banned != null) {
                            for(String user : new ArrayList<String>(new Gson().fromJson(banned, new TypeToken<ArrayList<String>>(){}.getType()))) {
                                list.append(" ").append(user).append(",");
                            }
                            list = new StringBuilder(list.substring(0, list.length() - 1));
                            Console.println("§light_greyThe current banned players are:§white" + list, false, Color.WHITE);
                        }
                    } else if(command.split(" ")[2].equalsIgnoreCase("name") || command.split(" ")[2].equalsIgnoreCase("username") || command.split(" ")[2].equalsIgnoreCase("names") || command.split(" ")[2].equalsIgnoreCase("usernames")) {
                        JsonArray banned = new JsonParser().parse(Reader.readJson(new File("./settings/user-settings/banned.json"))).getAsJsonObject().getAsJsonArray("banned");
                        StringBuilder list = new StringBuilder();
                        if(banned != null) {
                            for(String user : new ArrayList<String>(new Gson().fromJson(banned, new TypeToken<ArrayList<String>>(){}.getType()))) {
                                list.append(" ").append(Mojang.getUsername(user)).append(",");
                            }
                            list = new StringBuilder(list.substring(0, list.length() - 1));
                            Console.println("§light_greyThe current banned players are:§white" + list, false, Color.WHITE);
                        }
                    } else {
                        Console.println("§redInvalid use of command §light_greyban list§red. Try §light_greyban list (usernames/uuids)§red.", false, Color.RED);
                    }
                } else {
                    Console.println("§redInvalid use of command §light_greyban list§red. Try §light_greyban list (usernames/uuids)§red.", false, Color.RED);
                }

                if(command.split(" ")[1].equalsIgnoreCase("user") || command.split(" ")[1].equalsIgnoreCase("username")) {
                    if(command.split(" ").length != 3) {
                        Console.println("§redInvalid use of command §light_greyban user§red. Try §light_greyban user (username)§red.", false, Color.RED);
                        return;
                    }
                    JsonArray bannedUsers = new JsonParser().parse(Reader.readJson(new File("./settings/user-settings/banned.json"))).getAsJsonObject().getAsJsonArray("banned");
                    if(bannedUsers != null) {
                        if(!bannedUsers.contains(new JsonParser().parse(command.split(" ")[2]))) {
                            bannedUsers.add(Mojang.getUUID(command.split(" ")[2]));
                            Console.println("§greenSuccessfully banned the player §purple" + command.split(" ")[2] + " §green", false, Color.RED);
                        } else {
                            Console.println("§redThe player §purple" + command.split(" ")[2] + " §redis already banned", false, Color.RED);
                        }
                    } else {
                        bannedUsers.add(Mojang.getUUID(command.split(" ")[2]));
                    } // TODO: Change this to use the infractor instead of the bannedUsers JsonArray.
                } else {
                    Console.println("§redInvalid use of command §light_greyban user§red. Try §light_greyban user (username)§red.", false, Color.RED);
                }

                if(command.split(" ")[1].equalsIgnoreCase("uuid")) {

                } else {
                    Console.println("§redInvalid use of command §light_greyban uuid§red. Try §light_greyban uuid (uuid)§red.", false, Color.RED);
                }
            } else {
                Console.println("§redInvalid use of command §light_greyban§red. Try §light_greyban user (username)§red.", false, Color.RED);
            }
        }
    }
}
