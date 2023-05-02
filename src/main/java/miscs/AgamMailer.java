package miscs;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class AgamMailer {

    public static boolean send(String to,String sub,String msg){

        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");



            Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("agam.rimon.com@gmail.com","vbbdgcjcshlxxxtg");
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(sub);

            message.setText(msg, "UTF-8", "html");
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }



}
