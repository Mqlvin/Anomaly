package com.anomaly.io;

import com.anomaly.log.Logger;
import com.anomaly.log.Severity;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Reader {
    public static String readJson(File path) {
        if(path.exists()) {
            try {
                ArrayList<String> textFromFile = new ArrayList<>(Files.readAllLines(Paths.get(path.toString())));
                return String.join("", textFromFile);
            } catch(Exception e) {
                Logger.log(e.toString(), Severity.FATAL);
            }
        }
        return null;
    }

    public static ArrayList<String> readArrayList(File path) {
        if(path.exists()) {
            try {
                ArrayList<String> textFromFile = new ArrayList<>(Files.readAllLines(Paths.get(path.toString())));
                return textFromFile;
            } catch(Exception e) {
                Logger.log(e.toString(), Severity.FATAL);
            }
        }
        return null;
    }
}