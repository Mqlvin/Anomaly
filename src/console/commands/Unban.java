package console.commands;

import api.mojang.Mojang;
import console.Console;
import user.wrapper.Infractor;

import java.awt.*;
import java.util.ArrayList;

public class Unban {
    public static void unban(String command) {
        Infractor infractor = new Infractor();
        if(command.toLowerCase().startsWith("unban")) {
            if(command.split(" ").length == 2 || command.split(" ").length == 3) {
                if(command.split(" ")[1].equalsIgnoreCase("user") || command.split(" ")[1].equalsIgnoreCase("username")) {
                    if(command.split(" ").length != 3) {
                        Console.println("§redInvalid use of command §light_greyunban user§red. Try §light_greyunban user (username)§red.", false, Color.RED);
                        return;
                    }
                    ArrayList<String> bannedUsers = infractor.getBannedPlayers();
                    if(bannedUsers != null) {
                        if(bannedUsers.contains(Mojang.getUUID(command.split(" ")[2]))) {
                            infractor.unbanUser(Mojang.getUUID(command.split(" ")[2]));
                            Console.println("§whiteSuccessfully unbanned the player §purple" + command.split(" ")[2], false, Color.RED);
                        } else {
                            Console.println("§redThe player §purple" + command.split(" ")[2] + " §redis already unbanned", false, Color.RED);
                        }
                        return;
                    } else {
                        infractor.unbanUser(Mojang.getUUID(command.split(" ")[2]));
                        return;
                    }
                }

                if(command.split(" ")[1].equalsIgnoreCase("uuid")) {
                    if(command.split(" ").length != 3) {
                        Console.println("§redInvalid use of command §light_greyunban user§red. Try §light_greyunban user (username)§red.", false, Color.RED);
                        return;
                    }
                    ArrayList<String> bannedUsers = infractor.getBannedPlayers();
                    if(bannedUsers != null) {
                        if(bannedUsers.contains(command.split(" ")[2])) {
                            infractor.unbanUser(command.split(" ")[2]);
                            Console.println("§whiteSuccessfully unbanned the player §purple" + command.split(" ")[2], false, Color.RED);
                        } else {
                            Console.println("§redThe player §purple" + command.split(" ")[2] + " §redis already unbanned", false, Color.RED);
                        }
                        return;
                    } else {
                        infractor.unbanUser(command.split(" ")[2]);
                        return;
                    }
                }
            } else {
                Console.println("§redInvalid use of command §light_greyunban§red. Try §light_greyunban user (username)§red.", false, Color.RED);
            }
        }
    }
}
