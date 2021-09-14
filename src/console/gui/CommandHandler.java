package console.gui;

import console.Console;
import console.gui.commands.Clear;
import console.gui.commands.Get;
import console.gui.commands.Stop;

import java.awt.*;

public class CommandHandler {
    public void runCommand(String command) {
        if(command.toLowerCase().startsWith("clear")) {
            Clear.consoleClear(command);
            // End "clear" command.
        } else if(command.toLowerCase().startsWith("stop")) {
            Stop.consoleStop(command);
            // End "stop" command.
        } else if(command.toLowerCase().startsWith("get")) {
            Get.consoleGet(command);
            // End "get" command.
        }



        else {
            Console.println("Unknown command. Try \"help\" for a list of commands.", false, Color.RED);
        }
    }
}
