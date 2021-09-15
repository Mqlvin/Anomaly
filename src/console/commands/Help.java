package console.commands;

import console.Console;

import java.awt.*;

public class Help {
    public static void help(String command) {
        if(command.equalsIgnoreCase("help")) {
            // First page.
            Console.println("Commands help - Page 1 of 3:", false, Color.WHITE);
            Console.println("get help - Displays usages for the get command", false, Color.LIGHT_GRAY);
            Console.println("get UUID (username) - Gets UUID from username", false, Color.LIGHT_GRAY);
            Console.println("get username (UUID) - Gets username from UUID", false, Color.LIGHT_GRAY);
        } else if(command.toLowerCase().startsWith("help") && command.split(" ").length == 2) {
            if(command.split(" ")[1].equalsIgnoreCase("1")) {
                // First page.
                Console.println("Commands help - Page 1 of 3:", false, Color.WHITE);
                Console.println("get help - Displays usages for the get command", false, Color.LIGHT_GRAY);
                Console.println("get UUID (username) - Gets UUID from username", false, Color.LIGHT_GRAY);
                Console.println("get username (UUID) - Gets username from UUID", false, Color.LIGHT_GRAY);
            } else if(command.split(" ")[1].equalsIgnoreCase("2")) {
                // Second page.
                Console.println("Commands help - Page 2 of 3:", false, Color.WHITE);
                Console.println("clear - Clears all lines from the current console", false, Color.LIGHT_GRAY);
                Console.println("stop - Stops Anomaly", false, Color.LIGHT_GRAY);
                Console.println("help (int) - Brings up information about commands", false, Color.LIGHT_GRAY);
            } else if(command.split(" ")[1].equalsIgnoreCase("3")) {
                // Third page.
                Console.println("Commands help - Page 3 of 3:", false, Color.WHITE);
            } else {
                Console.println("Invalid page number. Try \"help (1-3)\".", false, Color.RED);
            }
        } else {
            Console.println("Invalid use of command \"help\". Try \"help (int)\".", false, Color.RED);
        }
    }

    /*
    Do at least 8-10 per page or else there will probably be like 20 pages.
     */
}
