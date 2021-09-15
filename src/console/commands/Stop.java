package console.commands;

import console.Console;

import java.awt.*;

public class Stop {
    public static void stop(String command) {
        if(command.equalsIgnoreCase("stop")) {
            Console.stop();
        } else {
            Console.println("Invalid use of command \"stop\". Try \"stop\".", false, Color.RED);
        }
    }
}
