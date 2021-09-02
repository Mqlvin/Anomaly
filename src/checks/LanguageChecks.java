package checks;

import api.mojang.Mojang;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.Reader;
import json.GetAllKeys;
import math.Calculators;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class LanguageChecks {
    public static void checkLanguage(String uuid, Double sensitivity) {
        JsonObject languageData = new JsonParser().parse(Reader.readJson(new File("./data/playerdata/" + uuid + "/languages.json"))).getAsJsonObject();
        if((languageData.size() < 2 && languageData.has("lastLanguage")) || (languageData.size() < 1 && !languageData.has("lastLanguage"))) {
            return;
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
            } else {
                continue;
            }
        }
        languages.add(Integer.parseInt(tempMax.replace("\"", "")));

        ArrayList<Double> response = new ArrayList<>(Calculators.getDifference(languages));
        Collections.sort(response);
        if(response.get(0) < sensitivity) { // The non-dynamic float can alter the sensitivity of the ACP.
            System.out.println(Mojang.getUsername(uuid) + "'s account may have been hacked.");
        } else {
            System.out.println(Mojang.getUsername(uuid) + "'s account should be safe.");
        }
    }
}

// - Get the most used, and the current used, add to arraylist, run algorithm, run check.
