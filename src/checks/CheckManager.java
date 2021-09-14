package checks;

import api.hypixel.Language;
import checks.wrapper.Checker;
import database.LanguageDB;
import misc.LanguageEnum;
import profile.SchedulerProfile;
import warn.Warn;

public class CheckManager {
    public static Checker check = new Checker();
    public static void runChecks(SchedulerProfile prof) {
        if(prof.doLanguageChecks()) {
            LanguageDB.addLanguage(prof.getUUID(), LanguageEnum.toEnum(Language.get(prof.getUUID(), prof.getKey())));
            if(check.checkLanguage(prof.getUUID(), prof.getLanguageSensitivity())) {
                Warn.sendWarning(prof);
            }
        }
    }
}
