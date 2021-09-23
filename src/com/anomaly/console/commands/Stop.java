package com.anomaly.console.commands;

import com.anomaly.console.Console;

import java.awt.*;

public class Stop {
    public static void stop(String command) {
        if(command.equalsIgnoreCase("stop")) {
            Console.stop();
        } else {
            Console.println("§redInvalid use of command §light_greystop§red. Try §light_greystop§red.", false, Color.RED);
        }
    }
}
