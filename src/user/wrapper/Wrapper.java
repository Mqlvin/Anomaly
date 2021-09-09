package user.wrapper;

import java.util.ArrayList;

public interface Wrapper {
    public String getUserID(String uuid);
    public ArrayList<String> getUUIDs(String ID);

    public void initialise(String uuid, String email, String key, String interval);
    public void setKey(String uuid, String key);
    public void setEmail(String uuid, String email);
    public void setInterval(String uuid, String interval);
}
