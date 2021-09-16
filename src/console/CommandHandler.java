package console;

import console.commands.*;

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
        } else if(command.toLowerCase().startsWith("ban")) {
            Ban.ban(command);
            // End of "ban" command.
        } else if(command.toLowerCase().startsWith("unban")) {
            Unban.unban(command);
            // End of "ban" command.
        }




        else {
            Console.println("§redUnknown command. Try §light_greyhelp§red for a list of commands.", false, Color.RED);
        }
    }
}
