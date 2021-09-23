package com.anomaly.console.commands;

import com.anomaly.api.mojang.Mojang;
import com.anomaly.console.Console;
import com.anomaly.profile.SchedulerProfile;
import com.anomaly.scheduler.Handler;

import java.awt.*;
import java.util.Arrays;

public class Scheduler {
    public static void schedule(String command) {
        if(command.toLowerCase().startsWith("scheduler")) {
            if(command.equalsIgnoreCase("scheduler")) {
                Console.println("§redInvalid use of the §light_greyscheduler §redcommand.", false, Color.RED);
                return;
            }
            if(command.split(" ")[1].equalsIgnoreCase("add")) {
                if(command.split(" ").length != 3) {
                    if(command.split(" ")[2].length() == 32) {
                        // The requested player is being inputted as a UUID.
                        Handler.checkAccounts.add(new SchedulerProfile(command.split(" ")[2]));
                    } else {
                        Handler.checkAccounts.add(new SchedulerProfile(Mojang.getUUID(command.split(" ")[2])));
                    }
                } else {
                    Console.println("§redInvalid use of the §light_greyscheduler §redcommand. Try §light_greyscheduler add (username)§red.", false, Color.RED);
                    return;
                }
            }
            if(command.split(" ")[1].equalsIgnoreCase("remove")) {
                if(command.split(" ").length != 3) {
                    if(command.split(" ")[2].length() == 32) {
                        // The requested player is being inputted as a UUID.
                        Handler.checkAccounts.remove(new SchedulerProfile(command.split(" ")[2]));
                    } else {
                        Handler.checkAccounts.remove(new SchedulerProfile(Mojang.getUUID(command.split(" ")[2])));
                    }
                } else {
                    Console.println("§redInvalid use of the §light_greyscheduler §redcommand. Try §light_greyscheduler remove (username)§red.", false, Color.RED);
                    return;
                }
            }
            System.out.println(Arrays.toString(command.split(" ")));
            if(command.split(" ")[1].equalsIgnoreCase("status")) {
                if(command.split(" ").length == 2) {
                    String build = "";
                    Console.println("§light_greyCurrent status for the scheduler:", false, Color.LIGHT_GRAY);
                    if(Handler.enabled) {
                        Console.println("§whiteScheduled: §greentrue", false, Color.WHITE);
                    } else {
                        Console.println("§whiteScheduled: §redfalse", false, Color.WHITE);
                    }
                    if(Handler.checkAccounts.size() >= 3) {
                        build = Handler.checkAccounts.get(0).getUsername() + ", " + Handler.checkAccounts.get(1).getUsername() + ", " + Handler.checkAccounts.get(2).getUsername() + ", and " + (Handler.checkAccounts.size() - 3) + " others.";
                    } else {
                        build = Handler.checkAccounts.get(0) + " and " + Handler.checkAccounts.get(1) + ".";
                    }
                    Console.println("§whitePlayers: §light_grey" + build, false, Color.WHITE);
                } else {
                    Console.println("§redInvalid use of the §light_greyscheduler §redcommand. Try §light_greyscheduler status§red.", false, Color.RED);
                    return;
                }
            }
        }
    }
}