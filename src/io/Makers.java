package io;

import log.Logger;
import log.Severity;

import java.io.File;

public class Makers {
    public static void makeDir(File path) {
        if(path.mkdir()) {
            Logger.log(path + " was successfully created.", Severity.INFO);
        } else {
            Logger.log(path + " directory was unsuccessfully created. Maybe it already exists?", Severity.WARN);
        }
    }

    public static void checkSettings(String uuid) {

        File file = new File("./settings/user-settings/");
    }
}
