package warn;

import mail.Sender;
import profile.SchedulerProfile;

public class Warn {
    public static void sendWarning(String uuid, SchedulerProfile profile) {
        if(profile.shouldSendEmail()) {
            Sender.sendEmail(uuid);

            // This class will send the warnings.
        }
    }
}
