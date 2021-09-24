package com.anomaly.checks;

import com.anomaly.api.hypixel.Language;
import com.anomaly.checks.wrapper.Checker;
import com.anomaly.database.LanguageDB;
import com.anomaly.misc.LanguageEnum;
import com.anomaly.profile.SchedulerProfile;
import com.anomaly.warn.Warn;

public class CheckManager {
    public static Checker check = new Checker();
    public static void runChecks(SchedulerProfile prof) {
        if(prof.doLanguageChecks()) {
            LanguageDB.addLanguage(prof.getUUID(), LanguageEnum.toEnum(Language.get(prof.getUUID(), prof.getKey())));
            if(check.checkLanguage(prof.getUUID(), prof.getLanguageSensitivity())) {
                Warn.sendWarning(prof);
            }
        }
        // TODO: Great place to do this, essentially only make one API request and past the database the response, then get the language from that.
    }
}
