package backup;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import date.subtractDays;
import io.Reader;
import log.Logger;
import log.Severity;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackupClearer {
        public static void removeOldBackups() {
            JsonObject obj = new JsonParser().parse(Reader.readJson(new File("./settings/settings.json"))).getAsJsonObject();
            if(obj.get("deleteOldBackups").toString().replace("\"", "").equalsIgnoreCase("true")) {
                String daysAgo = obj.get("keepBackupTime").toString().replace("\"", "");
                File[] backups = new File("./backup").listFiles();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date currentDate = new Date();
                for (File f : backups) {
                    if(f.isDirectory()) {
                        continue;
                    }
                    try {
                        String fileName = f.getName().replace("∕", "/").substring(0, f.getName().lastIndexOf(".")).substring(0, f.getName().lastIndexOf("-"));
                        if (sdf.parse(fileName).before(subtractDays.subtract(sdf.parse(sdf.format(currentDate)), Integer.parseInt(daysAgo)))) {
                            f.delete();
                        }
                    } catch (ParseException e) {
                        Logger.log(e.toString(), Severity.FATAL);
                    }
                }
            }
    }
}
