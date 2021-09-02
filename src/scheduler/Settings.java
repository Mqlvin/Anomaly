package scheduler;

import misc.GetIndex;

import java.util.ArrayList;

public class Settings {
    public static final ArrayList<Boolean> changeSettings = new ArrayList<>();
    public static final ArrayList<String> uuid = new ArrayList<>();

    public static void refreshSettings(String uuidToChange) {
        if(uuid.size() > 0) {
            changeSettings.set(GetIndex.getIndex(uuidToChange, uuid), true);
        }
    }
}
