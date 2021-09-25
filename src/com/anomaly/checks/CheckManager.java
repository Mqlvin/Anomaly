package com.anomaly.checks;

import com.anomaly.api.hypixel.Language;
import com.anomaly.checks.wrapper.Checker;
import com.anomaly.database.LanguageDB;
import com.anomaly.misc.LanguageEnum;
import com.anomaly.profile.SchedulerProfile;
import com.anomaly.warn.Warn;
import com.anomaly.wrapper.RequestType;
import com.anomaly.wrapper.Requester;

public class CheckManager {
    public static Checker check = new Checker();
    public static void runChecks(SchedulerProfile prof) {
        Requester response = new Requester(prof.getUUID(), RequestType.STATS);
        if(prof.doLanguageChecks()) {
            LanguageDB.addLanguage(prof.getUUID(), LanguageEnum.toEnum(Language.get(response.data())));
            if(check.checkLanguage(prof.getUUID(), prof.getLanguageSensitivity())) {
                Warn.sendWarning(prof);
            }
        }
    }
}
