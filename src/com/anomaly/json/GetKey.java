package com.anomaly.json;

import com.google.gson.JsonObject;

import java.util.ArrayList;

public class GetKey {
    public static String getKey(JsonObject obj, String value) {
        ArrayList<String> keys = GetAllKeys.get(obj);
        for(int i = 0; i < keys.size(); i++) {
            if(obj.get(keys.get(i)).toString().replace("\"", "").equalsIgnoreCase(value)) {
                return value;
            }
        }
        return null;
    }
}
