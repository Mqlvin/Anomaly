package com.anomaly.checks.wrapper;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.anomaly.io.Reader;
import com.anomaly.json.GetAllKeys;
import com.anomaly.math.Calculators;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Checker implements ChecksWr {
    @Override
    public Boolean checkLanguage(String uuid, Double sensitivity) {
        JsonObject languageData = new JsonParser().parse(Reader.readJson(new File("./data/playerdata/" + uuid + "/languages.json"))).getAsJsonObject();
        if((languageData.size() < 2 && languageData.has("lastLanguage")) || (languageData.size() < 1 && !languageData.has("lastLanguage"))) {
            return false;
        }
        ArrayList<Integer> languages = new ArrayList<>();
        String tempMax = "";

        for(String k : GetAllKeys.get(languageData)) {
            if(k.equalsIgnoreCase("lastLanguage")) {
                languages.add(Integer.parseInt(languageData.get(languageData.get(k).toString().replace("\"", "")).toString().replace("\"", "")));
            } else if(tempMax.equalsIgnoreCase("")) {
                tempMax = languageData.get(k.replace("\"", "")).toString();
            } else if(Integer.parseInt(languageData.get(k).toString().replace("\"", "")) > Integer.parseInt(tempMax.replace("\"", ""))) {
                tempMax = languageData.get(k.replace("\"", "")).toString();
            }
        }
        languages.add(Integer.parseInt(tempMax.replace("\"", "")));

        ArrayList<Double> response = new ArrayList<>(Calculators.getDifference(languages));
        Collections.sort(response);
        return response.get(0) < sensitivity;
    }
}
