package backup;

import log.Logger;
import log.Severity;
import java.io.File;

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

    public static void removeTemp() {
        Methods.deleteDir(new File("./backup/temp"));
    }
}
