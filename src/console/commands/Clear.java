package console.commands;

import console.Console;

import java.awt.*;

public class Clear {
    public static void clear(String command) {
        if(command.equalsIgnoreCase("clear")) {
            Console.clear();
        } else {
            Console.println("Invalid use of command \"clear\". Try \"clear\".", false, Color.RED);
        }
    }
}
