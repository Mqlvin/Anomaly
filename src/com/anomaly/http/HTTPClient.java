package com.anomaly.http;

import com.anomaly.log.Logger;
import com.anomaly.log.Severity;

import java.net.URL;
import java.util.Scanner;

public class HTTPClient {
    public static String requestJson(String URL) {
        String result;
        try {
            URL obj = new URL(URL);
            Scanner sc = new Scanner(obj.openStream());
            StringBuilder sb = new StringBuilder();
            while(sc.hasNext()) {
                sb.append(sc.next());
            }
            result = sb.toString().replaceAll("<[^>]*>", "");
        } catch(Exception e) {
            Logger.log(e.toString(), Severity.FATAL);
            return null;
        }
        return result;
    }

    public static String requestString(String URL) {
        String result = "";
        try {
            URL obj = new URL(URL);
            Scanner sc = new Scanner(obj.openStream());
            StringBuilder sb = new StringBuilder();
            while(sc.hasNext()) {
                sb.append(sc.next()).append(" ");
            }
            result = sb.toString();
        } catch(Exception e) {
            Logger.log(e.toString(), Severity.FATAL);
            return null;
        }
        return result;
    }
}
