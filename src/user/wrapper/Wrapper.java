package user.wrapper;

import java.util.ArrayList;

public interface Wrapper {
    public String getUserID(String UUID);
    public ArrayList<String> getUUIDs(String ID);

    public void initialise(String UUID, String email, String key, String interval);
    public void setKey(String UUID, String key);
    public void setEmail(String UUID, String email);
    public void setInterval(String UUID, String interval);
}
