package com.anomaly.handler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.anomaly.http.HTTPClient;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AnomalyStats {
    public static String version;
    public static ArrayList<String> authors;
    public static void getAnomalyStats() {
        JsonObject obj = new JsonParser().parse(HTTPClient.requestJson("https://anomaly.npkn.net/api/anomaly-stats")).getAsJsonObject();
        version = obj.get("version").toString().replace("\"", "");
        Type arrayType = new TypeToken<ArrayList<String>>(){}.getType();
        authors = new Gson().fromJson(obj.get("authors"), arrayType);
    }
}
