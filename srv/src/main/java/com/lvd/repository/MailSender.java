package com.lvd.repository;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * Created by charlesvienne on 18/04/2016.
 */
public class MailSender {
        private String to;
        private String subject;
        private String content;

        public Boolean send() {
            final String username = "charles.vienne@gmail.com";
            final String password = "";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(to));
                message.setSubject(subject);
                message.setText(content);

                Transport.send(message);

                return true;

            } catch (MessagingException e) {
                return false;
            }
        }

    public MailSender(String to, String subject, String content) {
        this.to = to;
        this.subject = subject;
        this.content = content;
    }
}
