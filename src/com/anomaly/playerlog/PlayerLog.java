package com.anomaly.playerlog;

import com.anomaly.io.Reader;
import com.anomaly.io.Writers;
import com.anomaly.log.Severity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PlayerLog {
    public static void log(String message, String uuid, Severity severity) {
        makeLogger(uuid);
        File logPath = new File("./data/playerdata/" + uuid + "/logs/log.txt");

        ArrayList<String> currentLog = Reader.readArrayList(logPath);
        if(currentLog != null) {
            while(currentLog.size() > 10000) {
                currentLog.remove(0);
            }
            currentLog.add(formatMessage(message, severity));
            Writers.writeFile(logPath, currentLog);
        } else {
            ArrayList<String> newLog = new ArrayList<>();
            newLog.add(formatMessage(message, severity));
            Writers.writeFile(logPath, newLog);
        }
    }

    public static void makeLogger(String uuid) {
        File logPath = new File("./data/playerdata/" + uuid + "/logs");
        if(!logPath.exists()) {
            logPath.mkdir();
        }
    }

    public static String formatMessage(String message, Severity severity) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Date currentDate = new Date();
        String finalized = "[" + sdf.format(currentDate) + "] [/" + severity.toString().toUpperCase() + "] " + message;
        String s = String.valueOf(finalized.charAt(finalized.length() - 1));
        if(!s.equalsIgnoreCase(".") || !s.equalsIgnoreCase("!") || !s.equalsIgnoreCase("?")) {
            finalized += ".";
        }
        return finalized;
    }
}