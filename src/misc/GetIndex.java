package misc;

import java.util.ArrayList;

public class GetIndex {
    public static int getIndex(String find, String[] array) {
        for(int i = 0; i < array.length; i++) {
            if(array[i].equalsIgnoreCase(find)) {
                return i;
            }
        }
        return -1;
    }
    public static int getIndex(String find, ArrayList<String> array) {
        for(int i = 0; i < array.size(); i++) {
            if(array.get(i).equalsIgnoreCase(find)) {
                return i;
            }
        }
        return -1;
    }
}
