package com.anomaly.log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Archiver {
    public static ArrayList<String> logToCompress, namesToNumbers = new ArrayList<>();
    public static File oldFile = new File("./runtime/logs/latest.txt");
    public static File logsDir = new File("./runtime/logs");
    public static File[] foundLogs;
    public static ArrayList<File> currentDateLogs = new ArrayList<>();
    public static String prevLogDate, newZipNumber;

    public static void zipLastLog() {
        if(oldFile.exists() && Logger.saveLog) {
            try {
                logToCompress = (ArrayList<String>) Files.readAllLines(Paths.get(oldFile.toString()));
            } catch(IOException e) {
                Logger.log(e.toString(), Severity.FATAL);
            }
            prevLogDate = logToCompress.get(0).split(" - ")[0].replace("/", "∕");
            //Now we have loaded the log we want to zip into the logToCompress array. We also have the file name date in the logSaveDate array.

            foundLogs = logsDir.listFiles();
            for(int i = 0; i < foundLogs.length; i++) {
                String parsedFileName = foundLogs[i].getName();
                if(parsedFileName.startsWith(prevLogDate)) {
                    currentDateLogs.add(foundLogs[i]);
                }
            }
            //Now we have an array filled with the previous logs' names (only logs from today).

            if(currentDateLogs.size() != 0) {
                for(int i = 0; i < currentDateLogs.size(); i++) {
                    String nameOfLog = currentDateLogs.get(i).getName();
                    String noExtension = nameOfLog.substring(0, nameOfLog.lastIndexOf('.'));
                    namesToNumbers.add(noExtension.substring(noExtension.lastIndexOf("-") + 1));
                }
                //Now we have the numbers at the end of each log.

                int[] numberOfZips = new int[namesToNumbers.size()];
                for(int i = 0; i < namesToNumbers.size(); i++) {
                    numberOfZips[i] = Integer.parseInt(namesToNumbers.get(i).replaceAll("[^\\d]", ""));
                }
                //Now we have an array filled with the file numbers, except now in an integer array!

                Arrays.sort(numberOfZips);
                newZipNumber = String.valueOf(numberOfZips[numberOfZips.length - 1] + 1);
                //Now we finally have the next number of log that we want.
            } else {
                newZipNumber = "1";
            }

            compressLog();
        } else {
            Logger.log("A log was called to be archived but no log was found.", Severity.WARN);
        }

        /*
        Now I am going to zip the file.
        I don't really need to save space but it just seems easier to do.
        When a backup is called, it SHOULD only backup the logs from the day the backup is called.
         */
    }

    public static void compressLog() {
        try {
            FileOutputStream fos = new FileOutputStream("./runtime/logs/" + prevLogDate + "-" + newZipNumber + ".zip");
            ZipOutputStream zos = new ZipOutputStream(fos);
            File logZip = new File(oldFile.toString());
            FileInputStream fis = new FileInputStream(logZip);

            ZipEntry zipEntry = new ZipEntry(logZip.getName());
            zos.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;

            while((length = fis.read(bytes)) >= 0) {
                zos.write(bytes, 0, length);
            }
            zos.close();
            fis.close();
            fos.close();
            Logger.log("The previous log was successfully zipped.", Severity.INFO);
        } catch(IOException e) {
            Logger.log(e.toString(), Severity.FATAL);
        }
    }
}