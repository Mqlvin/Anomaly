package console.commands;

import console.Console;

import java.awt.*;

public class Help {
    public static void help(String command) {
        if(command.equalsIgnoreCase("help")) {
            // First page.
            Console.println("§light_greyCommands help - Page §white1 §light_greyof §white3§light_grey:", false, Color.WHITE);
            Console.println("§whiteget help §light_grey- Displays usages for the get command", false, Color.LIGHT_GRAY);
            Console.println("§whiteget UUID (username) §light_grey- Gets UUID from username", false, Color.LIGHT_GRAY);
            Console.println("§whiteget username (UUID) §light_grey- Gets username from UUID", false, Color.LIGHT_GRAY);
        } else if(command.toLowerCase().startsWith("help") && command.split(" ").length == 2) {
            if(command.split(" ")[1].equalsIgnoreCase("1")) {
                // First page.
                Console.println("§light_greyCommands help - Page §white1 §light_greyof §white3§light_grey:", false, Color.WHITE);
                Console.println("§whiteget help §light_grey- Displays usages for the get command", false, Color.LIGHT_GRAY);
                Console.println("§whiteget UUID (username) §light_grey- Gets UUID from username", false, Color.LIGHT_GRAY);
                Console.println("§whiteget username (UUID) §light_grey- Gets username from UUID", false, Color.LIGHT_GRAY);
            } else if(command.split(" ")[1].equalsIgnoreCase("2")) {
                // Second page.
                Console.println("§light_greyCommands help - Page §white2 §light_greyof §white3§light_grey:", false, Color.WHITE);
                Console.println("§whiteclear §light_grey- Clears all lines from the current console", false, Color.LIGHT_GRAY);
                Console.println("§whitestop §light_grey- Stops Anomaly", false, Color.LIGHT_GRAY);
                Console.println("§whitehelp (int) §light_grey- Brings up information about commands", false, Color.LIGHT_GRAY);
            } else if(command.split(" ")[1].equalsIgnoreCase("3")) {
                // Third page.
                Console.println("§light_greyCommands help - Page §white3 §light_greyof §white3§light_grey:", false, Color.WHITE);
            } else {
                Console.println("§redInvalid page number. Try §light_greyhelp (1-3)§red.", false, Color.RED);
            }
        } else {
            Console.println("§redInvalid use of command §light_greyhelp§red. Try §light_greyhelp (int)§red.", false, Color.RED);
        }
    }

    /*
    Do at least 8-10 per page or else there will probably be like 20 pages.
     */
}
