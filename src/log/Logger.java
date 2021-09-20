package log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.Reader;
import io.Writers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.ArrayList;

import static http.HTTPClient.requestJson;

public class Logger {
    public static ArrayList<String> appLog = new ArrayList<>();
    public static String[] sentenceEnders = {"!",".","?"};
    public static Boolean saveLog;

    public static void log(String message, Severity level) {
        if(saveLog) {
            SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();

            if (!(Arrays.asList(sentenceEnders).contains(message.substring(message.length() - 1)))) {
                message = message + ".";
            } //Curing OCD by making every line end with punctuation.

            appLog.add("[" + time.format(date) + "] [/" + level.toString() + "] " + message);

            saveLog();
        }
    }

    public static void saveLog() {
        if(appLog.size() <= 6) {
        } else {
            try {
                PrintWriter pw = new PrintWriter(new FileOutputStream("./runtime/logs/latest.txt"));
                for (String element : appLog)
                    pw.println(element);
                pw.close();
            } catch (IOException e) {
                log(e.toString(), Severity.FATAL);
            }
        }
    }

    public static void dumpAll() {
        if(saveLog) {
            try {
                PrintWriter pw = new PrintWriter(new FileOutputStream("./runtime/logs/latest.txt"));
                for (String element : appLog)
                    pw.println(element);
                pw.close();
            } catch (IOException e) {
                log(e.toString(), Severity.FATAL);
            }
        }
    }
    /*
    dumpAll() is run at the start to dump the logs before the program exits (assuming there are <6 logs). This could be temporary but I will leave it here.
    Please only call this method at the start, as it automatically dumps the rest of the logs later.
     */

    public static void addLogHeader() {
        if(saveLog) {
            SimpleDateFormat time = new SimpleDateFormat("dd/MM/YYYY - kk:mm");
            Date date = new Date();

            appLog.add(time.format(date) + " - Anomaly runtime log.");
        }
    }

    public static void initialiseLogs() {
        try {
            File file = new File("./settings/settings.json");
            if(file.exists()) {
            } else {
                file.createNewFile();
                Writers.writeFile(file, requestJson("https://anomaly.npkn.net/api/settings"));
            }
        } catch(IOException e) {
            Logger.log(e.toString(), Severity.FATAL);
        }
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(Reader.readJson(new File("./settings/settings.json"))).getAsJsonObject();
        if(obj.get("keepLogs").toString().replace("\"", "").equalsIgnoreCase("true")) {
            saveLog = true;
        } else {
            saveLog = false;
        }
    }
}
