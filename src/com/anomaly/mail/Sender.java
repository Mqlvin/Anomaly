package com.anomaly.mail;

import com.anomaly.log.Logger;
import com.anomaly.log.Severity;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Sender {
    public static void sendEmail(String uuid) {
        String html = Generator.generate(uuid);

        Properties p = new Properties();
        p.put("com.anomaly.mail.smtp.host", "smtp.gmail.com");
        p.put("com.anomaly.mail.smtp.socketFactory.port", "465");
        p.put("com.anomaly.mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.put("com.anomaly.mail.smtp.auth", "true");
        p.put("com.anomaly.mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(p, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("PricklyDotCactus@gmail.com", "sp1keygr33npl4nt");
            }
        });

        try {
            Message mail = new MimeMessage(session);
            mail.setFrom(new InternetAddress("pricklydotcactus@gmail.com"));
            mail.setRecipients(Message.RecipientType.TO, InternetAddress.parse("henryviant@gmail.com"));
            mail.setSubject("Account security alert! - com.anomaly.Anomaly");
            mail.setContent(html, "text/html");
            Transport.send(mail);
        } catch(MessagingException e) {
            Logger.log(e.toString(), Severity.FATAL);
        }
    }
}
