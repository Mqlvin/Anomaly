package com.anomaly.startup;

import com.anomaly.backup.Backup;
import com.anomaly.backup.BackupClearer;
import com.anomaly.console.Console;
import com.anomaly.handler.AnomalyStats;
import com.anomaly.io.Dirs;
import com.anomaly.io.Files;
import com.anomaly.log.Archiver;
import com.anomaly.log.LogClearer;
import com.anomaly.log.Logger;
import com.anomaly.user.Patch;

import java.awt.*;

public class Start {
    public static void start() {
        AnomalyStats.getAnomalyStats();
        new Console();
        Console.println(" ", false, Color.BLACK);
        Console.println("§white$ §light_greyJFrame initialised.", false, Color.WHITE);
        Dirs.startupDirs();
        Console.println("§white$ §light_greyCreated neccessary directories.", false, Color.WHITE);
        Files.startupFiles();
        Console.println("§white$ §light_greyCreated neccessary files.", false, Color.WHITE);

        Logger.initialiseLogs();
        Logger.addLogHeader();
        Console.println("§white$ §light_greyInitialised logs.", false, Color.WHITE);
        Archiver.zipLastLog();
        Console.println("§white$ §light_greyCompressing previous logs.", false, Color.WHITE);

        Logger.dumpAll();
        Backup.backup();
        Console.println("§white$ §light_greyCreating a backup.", false, Color.WHITE);

        LogClearer.removeOldLogs();
        Console.println("§white$ §light_greyRemoving old logs.", false, Color.WHITE);
        BackupClearer.removeOldBackups();
        Console.println("§white$ §light_greyRemoving old backups.", false, Color.WHITE);

        Patch.patchAllUsers();
        Console.println("§white$ §light_greyPatching user settings.", false, Color.WHITE);
        Console.println("\n", false, Color.WHITE);
    }
}
