package checks;

import api.hypixel.Language;
import checks.wrapper.Checker;
import database.LanguageDB;
import profile.SchedulerProfile;
import warn.Warn;

public class CheckManager {
    public static Checker check = new Checker();
    public static void runChecks(SchedulerProfile prof) {
        if(prof.doLanguageChecks()) {
            if(check.checkLanguage(prof.getUUID(), prof.getLanguageSensitivity())) {
                Warn.sendWarning(prof);
            }
            LanguageDB.addLanguage(prof.getUUID(), LanguageDB.toEnum(Language.get(prof.getUUID(), prof.getKey())));
        }
    }
}
