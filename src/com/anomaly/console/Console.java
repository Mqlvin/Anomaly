package com.anomaly.console;

import java.awt.*;

public class Console {
    public static GUI console = new GUI();
    public static Boolean ended = false;

    public static void println(String line, Boolean trace, Color c) {
        console.println(line, trace, c);
    }

    public static void clear() {
        console.clear();
    }

    public static void stop() {
        console.stop();
    }

    public static void deleteLast() {
        console.delLast();
    }
}
