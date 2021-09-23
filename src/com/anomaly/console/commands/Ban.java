package com.anomaly.console.commands;

import com.anomaly.api.mojang.Mojang;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.anomaly.console.Console;
import com.anomaly.user.wrapper.Infractor;

import java.awt.*;
import java.util.ArrayList;

public class Ban {
    public static void ban(String command) {
        Infractor infractor = new Infractor();
        if(command.toLowerCase().startsWith("ban")) {
            if(command.split(" ").length == 2 || command.split(" ").length == 3) {
                if(command.split(" ")[1].equalsIgnoreCase("list")) {
                    if(command.split(" ").length == 2) {
                        Console.println("§redInvalid use of command §light_greyban§red. Try §light_greyban list (usernames/uuids)§red.", false, Color.RED);
                        return;
                    }
                    if(command.split(" ")[2].equalsIgnoreCase("uuid") || command.split(" ")[2].equalsIgnoreCase("uuids")) {
                        ArrayList<String> banned = infractor.getBannedPlayers();
                        StringBuilder list = new StringBuilder();
                        if (banned != null) {
                            for (String user : banned) {
                                list.append(" ").append(user).append(",");
                            }
                            list = new StringBuilder(list.substring(0, list.length() - 1));
                            Console.println("§light_greyThe current banned players are:§white" + list, false, Color.WHITE);
                        }
                        return;
                    } else if (command.split(" ")[2].equalsIgnoreCase("name") || command.split(" ")[2].equalsIgnoreCase("username") || command.split(" ")[2].equalsIgnoreCase("names") || command.split(" ")[2].equalsIgnoreCase("usernames")) {
                        ArrayList<String> banned = infractor.getBannedPlayers();
                        StringBuilder list = new StringBuilder();
                        if (banned != null) {
                            for (String user : banned) {
                                list.append(" ").append(Mojang.getUsername(user)).append(",");
                            }
                            list = new StringBuilder(list.substring(0, list.length() - 1));
                            Console.println("§light_greyThe current banned players are:§white" + list, false, Color.WHITE);
                            return;
                        }
                    } else {
                        Console.println("§redInvalid use of command §light_greyban list§red. Try §light_greyban list (usernames/uuids)§red.", false, Color.RED);
                        return;
                    }
                }

                if(command.split(" ")[1].equalsIgnoreCase("com/anomaly/user") || command.split(" ")[1].equalsIgnoreCase("username")) {
                    if(command.split(" ").length != 3) {
                        Console.println("§redInvalid use of command §light_greyban com.anomaly.user§red. Try §light_greyban com.anomaly.user (username)§red.", false, Color.RED);
                        return;
                    }
                    ArrayList<String> bannedUsers = infractor.getBannedPlayers();
                    if(bannedUsers != null) {
                        if(!bannedUsers.contains(Mojang.getUUID(command.split(" ")[2]))) {
                            infractor.banUser(Mojang.getUUID(command.split(" ")[2]));
                            Console.println("§whiteSuccessfully banned the player §purple" + command.split(" ")[2], false, Color.RED);
                        } else {
                            Console.println("§redThe player §purple" + command.split(" ")[2] + " §redis already banned", false, Color.RED);
                        }
                        return;
                    } else {
                        infractor.banUser(Mojang.getUUID(command.split(" ")[2]));
                        return;
                    }
                }

                if(command.split(" ")[1].equalsIgnoreCase("uuid")) {
                    if(command.split(" ").length != 3) {
                        Console.println("§redInvalid use of command §light_greyban com.anomaly.user§red. Try §light_greyban com.anomaly.user (username)§red.", false, Color.RED);
                        return;
                    }
                    ArrayList<String> bannedUsers = infractor.getBannedPlayers();
                    if(bannedUsers != null) {
                        if(!bannedUsers.contains(command.split(" ")[2])) {
                            infractor.banUser(command.split(" ")[2]);
                            Console.println("§whiteSuccessfully banned the player §purple" + command.split(" ")[2], false, Color.RED);
                        } else {
                            Console.println("§redThe player §purple" + command.split(" ")[2] + " §redis already banned", false, Color.RED);
                        }
                        return;
                    } else {
                        infractor.banUser(command.split(" ")[2]);
                        return;
                    }
                }
            } else {
                Console.println("§redInvalid use of command §light_greyban§red. Try §light_greyban com.anomaly.user (username)§red.", false, Color.RED);
            }
        }
    }
}
