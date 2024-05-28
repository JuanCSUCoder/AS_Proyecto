
import javax.mail.*;
import javax.mail.internet.*;

import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.Properties;

public class Mail {

    static void send_mail(JSONPObject contenido, String email) {
        final String username = "malbororitual@gmail.com";
        final String password = "faiy wzau slgq wedn";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLS
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("malbororitual@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Envio Realizado Orden: ");
            message.setText("hol");

            Transport.send(message);

            // System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}