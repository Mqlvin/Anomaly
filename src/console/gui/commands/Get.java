package console.gui.commands;

import api.mojang.Mojang;
import console.Console;

import java.awt.*;

public class Get {
    public static void consoleGet(String command) {
        if(command.toLowerCase().startsWith("get uuid")) {
            if(command.split(" ").length == 3) {
                String uuid = Mojang.getUUID(command.split(" ")[2]);
                if(uuid == null) {
                    Console.println("The player \"" + command.split(" ")[2] + "\" does not exist, or the Mojang API is down.", false, Color.RED);
                } else {
                    Console.println("The UUID for the player \"" + command.split(" ")[2] + "\" is:", false, Color.WHITE);
                    Console.println(uuid, false, Color.GREEN);
                }
            } else {
                Console.println("Invalid use of command \"get uuid\". Try \"get uuid (username)\".", false, Color.RED);
            }
        } else if(command.toLowerCase().startsWith("get username") || command.toLowerCase().startsWith("get name")) {
            if(command.split(" ").length == 3) {
                String uuid = Mojang.getUsername(command.split(" ")[2]);
                if(uuid == null) {
                    Console.println("The player \"" + command.split(" ")[2] + "\" does not exist, or the Mojang API is down.", false, Color.RED);
                } else {
                    Console.println("The username for the player \"" + command.split(" ")[2] + "\" is:", false, Color.WHITE);
                    Console.println(uuid, false, Color.GREEN);
                }
            } else {
                Console.println("Invalid use of command \"get username\". Try \"get username (uuid)\".", false, Color.RED);
            }
        } else if(command.toLowerCase().startsWith("get help")) {
            if(command.split(" ").length == 2) {
                Console.println("Usages for the command \"help\":", false, Color.WHITE);
                Console.println("get help - Displays usages for the get command", false, Color.LIGHT_GRAY);
                Console.println("get UUID (username) - Gets UUID from username", false, Color.LIGHT_GRAY);
                Console.println("get username (UUID) - Gets username from UUID", false, Color.LIGHT_GRAY);
            } else {
                Console.println("Invalid use of command \"get help\". Try \"get help\".", false, Color.RED);
            }
        } else {
            Console.println("Invalid use of the command \"get\". Try \"get help\".", false, Color.RED);
        }
    }
}
