package warns;

import com.google.gson.JsonObject;
import mail.Sender;

public class Warn {
    public static void sendWarning(String uuid, JsonObject settings) {
        if(settings.get("sendEmail").toString().equalsIgnoreCase("true")) {
            Sender.sendEmail(uuid);

            // This class will send the warnings.
        }
    }
}
