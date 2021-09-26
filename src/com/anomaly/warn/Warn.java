package com.anomaly.warn;

import com.anomaly.log.Severity;
import com.anomaly.mail.Sender;
import com.anomaly.playerlog.PlayerLog;
import com.anomaly.profile.SchedulerProfile;

public class Warn {
    public static void sendWarning(SchedulerProfile prof) {
        System.out.println(prof.shouldSendEmail());
        System.out.println(prof.getEmail());
        if(prof.shouldSendEmail() && !prof.getEmail().equalsIgnoreCase("%email%")) {
            Sender.sendEmail(prof.getUUID());
        } else {
            PlayerLog.log("A warning was meant to be sent, however the email was invalid or disabled.", prof.getUUID(), Severity.WARN);
        }
    }
}
