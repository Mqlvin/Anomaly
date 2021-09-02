package user.wrapper;

import java.util.ArrayList;

public interface Wrapper {
    public String getUserID(String UUID);
    public ArrayList<String> getUUIDs(String ID);

    public void setKey(String UUID, String key);
}
