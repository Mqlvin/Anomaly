package console;

import console.commands.Clear;
import console.commands.Get;
import console.commands.Help;
import console.commands.Stop;

import java.awt.*;

public class CommandHandler {
    public void runCommand(String command) {
        if(command.toLowerCase().startsWith("clear")) {
            Clear.clear(command);
            // End "clear" command.
        } else if(command.toLowerCase().startsWith("stop")) {
            Stop.stop(command);
            // End "stop" command.
        } else if(command.toLowerCase().startsWith("get")) {
            Get.get(command);
            // End "get" command.
        } else if(command.toLowerCase().startsWith("help")) {
            Help.help(command);
            // End of "help" command.
        }



        else {
            Console.println("Unknown command. Try \"help\" for a list of commands.", false, Color.RED);
        }
    }
}
