package scheduler;

import misc.GetIndex;

import java.util.ArrayList;

public class Handler {

    /*
    "players" - Usage: No current usage but can be used to see all loaded players.
    "running" - Usage: For the individual schedulers to detect whether their player should be running detections.
    "changeSettings" - Usage: To detect when the schedulers need to update their settings, including JSON settings, running e.g.
     */

    public static volatile ArrayList<String> players = new ArrayList<>();
    public static volatile ArrayList<Boolean> running = new ArrayList<>();
    public static volatile ArrayList<Boolean> changeSettings = new ArrayList<>();

    public static Integer registerPlayer(String uuid) {
        players.add(uuid);
        running.add(false);
        changeSettings.add(false);

        return null;
    }

    public static void setRunning(String uuid, Boolean state) {
        running.set(GetIndex.getIndex(uuid, players), state);
    }

    public static void changeSettings(String uuid, Boolean state) {
        changeSettings.set(GetIndex.getIndex(uuid, players), state);
    }

    public static Boolean shouldRun(String uuid) {
        return running.get(GetIndex.getIndex(uuid, players));
    }

    public static Boolean shouldChangeSettings(String uuid) {
        return changeSettings.get(GetIndex.getIndex(uuid, players));
    }
}
