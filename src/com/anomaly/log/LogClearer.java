package com.anomaly.log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.anomaly.date.subtractDays;
import com.anomaly.io.Reader;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogClearer {
    public static void removeOldLogs() {
        JsonObject obj = new JsonParser().parse(Reader.readJson(new File("./settings/settings.json"))).getAsJsonObject();
        if(obj.get("deleteOldLogs").toString().replace("\"", "") == "true") {
            String daysAgo = obj.get("keepLogTime").toString().replace("\"", "");
            File[] logs = new File("./runtime/logs").listFiles();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date currentDate = new Date();
            for (File f : logs) {
                if(f.getName().equalsIgnoreCase("latest.txt")) {
                    continue;
                }
                try {
                    String fileName = f.getName().replace("∕", "/").substring(0, f.getName().lastIndexOf(".")).substring(0, f.getName().lastIndexOf("-"));
                    if(sdf.parse(fileName).before(subtractDays.subtract(sdf.parse(sdf.format(currentDate)), Integer.parseInt(daysAgo)))) {
                        f.delete();
                    }
                } catch(ParseException e) {
                    Logger.log(e.toString(), Severity.FATAL);
                }
            }
        }
    }
}