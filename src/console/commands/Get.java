package console.commands;

import api.mojang.Mojang;
import console.Console;

import java.awt.*;

public class Get {
    public static void get(String command) {
        if(command.toLowerCase().startsWith("get uuid")) {
            if(command.split(" ").length == 3) {
                String uuid = Mojang.getUUID(command.split(" ")[2]);
                if(uuid == null) {
                    Console.println("§redThe player §purple" + command.split(" ")[2] + " §reddoes not exist, or the Mojang API is down.", false, Color.RED);
                } else {
                    Console.println("§whiteThe UUID for the player §purple" + command.split(" ")[2] + " §whiteis: §green" + uuid, false, Color.WHITE);
                }
            } else {
                Console.println("§redInvalid use of command §light_greyget uuid§red. Try §light_greyget uuid (username)§red.", false, Color.RED);
            }
        } else if(command.toLowerCase().startsWith("get username") || command.toLowerCase().startsWith("get name")) {
            if(command.split(" ").length == 3) {
                String username = Mojang.getUsername(command.split(" ")[2]);
                if(username == null) {
                    Console.println("§redThe player §purple" + command.split(" ")[2] + " §reddoes not exist, or the Mojang API is down.", false, Color.RED);
                } else {
                    Console.println("§whiteThe username for the player §purple" + command.split(" ")[2] + " §whiteis: §green" + username, false, Color.WHITE);
                }
            } else {
                Console.println("§redInvalid use of command §light_greyget username§red. Try §light_greyget username (uuid)§red.", false, Color.RED);
            }
        } else if(command.toLowerCase().startsWith("get help")) {
            if(command.split(" ").length == 2) {
                Console.println("§light_greyUsages for the command §whitehelp§light_grey:", false, Color.WHITE);
                Console.println("§whiteget help §light_grey- Displays usages for the get command", false, Color.LIGHT_GRAY);
                Console.println("§whiteget UUID (username) §light_grey- Gets UUID from username", false, Color.LIGHT_GRAY);
                Console.println("§whiteget username (UUID) §light_grey- Gets username from UUID", false, Color.LIGHT_GRAY);
            } else {
                Console.println("§whiteInvalid use of command §light_greyget help§red. Try §light_greyget help§red.", false, Color.RED);
            }
        } else {
            Console.println("§redInvalid use of the command §light_greyget§red. Try §light_greyget help§red.", false, Color.RED);
        }
    }
}
