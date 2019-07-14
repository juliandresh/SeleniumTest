package com.chubb.utilities.email;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class EmailSender {
    public static void main(String[] args)
    {
        new EmailSender().sendEmail();
    }

    public void sendEmail() {

        String to = "Julian.HernandezAlmanza@chubb.com";
        String from = "Julian.HernandezAlmanza@chubb.com";

        Properties props = new Properties();
        props.put("mail.host", "172.25.98.XX");
        props.put("mail.smtp.port", "XX");
        props.put("mail.smtp.auth","false");

        Session session = Session.getDefaultInstance(props, null);
        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Testing Subject");
            //Send the actual HTML message, as big as you like
            //message.setContent("<h1>This is actual message embedded in HTML tags</h1>", "text/html");
            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setContent("<b>This is message body</b>", "text/html");


            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "C:\\Users\\jahern\\volshext.log";//C:\\Selenium\\Marine\\pdf\\20190517_142359\\Marine.pdf";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("Marine.log");
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        }
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}



