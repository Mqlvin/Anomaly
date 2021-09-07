package schedulerrr;

import misc.GetIndex;

import java.util.ArrayList;

public class Settings {
    public static ArrayList<Boolean> changeSettings = new ArrayList<>();
    public static ArrayList<Boolean> isRunning = new ArrayList<>();
    public static ArrayList<String> uuids = new ArrayList<>();

    public static void refreshSettings(String uuid) {
        if(uuids.size() > 0) {
            changeSettings.set(GetIndex.getIndex(uuid, uuids), true);
        }
    }

    public static void setRunning(String uuid, Boolean running) {
        if(uuids.size() > 0) {
            isRunning.set(GetIndex.getIndex(uuid, uuids), running);
        }
    }
}
