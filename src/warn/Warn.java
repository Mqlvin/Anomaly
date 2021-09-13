package warn;

import log.Severity;
import mail.Sender;
import playerlog.PlayerLog;
import profile.SchedulerProfile;

public class Warn {
    public static void sendWarning(SchedulerProfile prof) {
        if(prof.shouldSendEmail() && !prof.getEmail().equalsIgnoreCase("%email%")) {
            Sender.sendEmail(prof.getUUID());
        } else {
            PlayerLog.log("A warning was meant to be sent, however the email was invalid or disabled.", prof.getUUID(), Severity.WARN);
        }
    }
}
