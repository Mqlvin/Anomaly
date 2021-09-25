package com.anomaly.console.commands;

import com.anomaly.console.CommandHandler;
import com.anomaly.console.Console;

import java.awt.*;

public class ToggleChecks {
    public static void toggleChecks(String command) {
        if(command.equalsIgnoreCase("togglecheckmessages")) {
            CommandHandler.showCheckMessages = !CommandHandler.showCheckMessages;
            if(CommandHandler.showCheckMessages == true) {
                Console.println("§light_greyCheck messages have been toggled §greenon§light_grey.", false, Color.WHITE);
            } else {
                Console.println("§light_greyCheck messages have been toggled §redoff§light_grey.", false, Color.WHITE);
            }
        } else {
            Console.println("§redInvalid use of command §light_greytogglecheckmessages§red. Try §light_greytogglecheckmessages§red.", false, Color.RED);
        }
    }
}
