package com.anomaly.misc;

import com.anomaly.database.Languages;

public class LanguageEnum {
    public static Enum<Languages> toEnum(String language) {
        for(Languages l : Languages.values()) {
            if(l.toString().equalsIgnoreCase(language)) {
                return l;
            }
        }
        return Languages.OTHER;
    }
}
