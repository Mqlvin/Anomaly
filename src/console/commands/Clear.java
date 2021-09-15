package console.commands;

import console.Console;

import java.awt.*;

public class Clear {
    public static void clear(String command) {
        if(command.equalsIgnoreCase("clear")) {
            Console.clear();
        } else {
            Console.println("§redInvalid use of command §light_greyclear§red. Try §light_greyclear§red.", false, Color.RED);
        }
    }
}
