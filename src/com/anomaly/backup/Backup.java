package com.anomaly.backup;

import com.anomaly.log.Logger;
import com.anomaly.log.Severity;

public class Backup {
    public static void backup() {
        try {
            Methods.copyDirectory();
            Methods.removeUselessFiles();
            Methods.createZip();
            Logger.log("Backup successfully created!", Severity.INFO);
        } catch(Exception e) {
            Logger.log(e.toString(), Severity.FATAL);
        }
    }
}
