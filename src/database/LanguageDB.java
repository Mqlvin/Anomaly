package database;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.Reader;
import io.Writers;
import json.GetAllKeys;

import java.io.File;
import java.util.ArrayList;

public class LanguageDB {
    public static void addLanguage(String uuid, Enum<Languages> language) {
        JsonObject obj = new JsonParser().parse(Reader.readJson(new File("./data/playerdata/" + uuid + "/languages.json"))).getAsJsonObject();
        if(obj.has(language.toString())) {
            ArrayList<String> usedLanguages = new ArrayList<>(GetAllKeys.get(obj));
            int timesUsed = 0;
            for(String l : usedLanguages) {
                if(l.equalsIgnoreCase(language.toString())) {
                    timesUsed = Integer.parseInt(obj.get(l).toString().replaceAll("\"", "")) + 1;
                    obj.remove(l);
                    break;
                }
            }
            obj.add(language.toString(), new JsonParser().parse("\"" + timesUsed + "\""));
        } else {
            obj.add(language.toString(), new JsonParser().parse("\"1\""));
        }
        if(obj.has("lastLanguage")) {
            obj.remove("lastLanguage");
        }
        obj.add("lastLanguage", new JsonParser().parse(language.toString()));
        Writers.writeFile(new File("./data/playerdata/" + uuid + "/languages.json"), obj.toString());
    }

    public static Enum<Languages> toEnum(String language) {
        for(Languages l : Languages.values()) {
            if(l.toString().equalsIgnoreCase(language)) {
                return l;
            }
        }
        return Languages.OTHER;
    }
}
