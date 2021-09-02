package io;

import java.io.File;

public class Dirs {
    public static void startupDirs() {
        Makers.makeDir(new File("./runtime"));
        Makers.makeDir(new File("./runtime/user-settings"));
        Makers.makeDir(new File("./runtime/settings"));
        Makers.makeDir(new File("./runtime/logs"));

        Makers.makeDir(new File("./backup"));
        Makers.makeDir(new File("./settings"));
        Makers.makeDir(new File("./data"));
        Makers.makeDir(new File("./data/cache"));
        Makers.makeDir(new File("./data/settings"));
        Makers.makeDir(new File("./data/playerdata"));
        Makers.makeDir(new File("./data/response"));
    }
}
