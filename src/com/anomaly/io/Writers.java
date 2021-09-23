package com.anomaly.io;

import com.anomaly.log.Logger;
import com.anomaly.log.Severity;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

public class Writers {
    public static void writeFile(File path, ArrayList<String> data) {
        try {
            FileWriter fw = new FileWriter(path);
            for(String str : data) {
                fw.write(str + System.lineSeparator());
            }
            fw.close();
        } catch(IOException e) {
            Logger.log(e.toString(), Severity.FATAL);
        }
    }

    public static void writeFile(File path, String[] data) {
        ArrayList<String> array = new ArrayList<>(Arrays.asList(data));
        try {
            FileWriter fw = new FileWriter(path);
            for(String str : array) {
                fw.write(str + System.lineSeparator());
            }
            fw.close();
        } catch(IOException e) {
            Logger.log(e.toString(), Severity.FATAL);
        }
    }

    public static void writeFile(File path, String data) {
        try {
            Files.write(path.toPath(), data.getBytes());
        } catch(IOException e) {
            Logger.log(e.toString(), Severity.FATAL);
        }
    }

    public static void writeFile(File path, String data, String regex) {
        ArrayList<String> array = new ArrayList<>(Arrays.asList(data.split(regex)));
        try {
            FileWriter fw = new FileWriter(path);
            for(String str : array) {
                fw.write(str + System.lineSeparator());
            }
            fw.close();
        } catch(IOException e) {
            Logger.log(e.toString(), Severity.FATAL);
        }
    }
}
