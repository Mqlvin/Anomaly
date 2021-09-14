package console.gui.commands;

import console.Console;

import java.awt.*;

public class Clear {
    public static void consoleClear(String command) {
        if(command.equalsIgnoreCase("clear")) {
            Console.clear();
        } else {
            Console.println("Invalid use of command \"clear\". Try \"clear\".", false, Color.RED);
        }
    }
}