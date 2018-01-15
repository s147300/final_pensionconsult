package com.example.jonathanlarsen.pensionconsultmainpage.Logic;

/*
This class has been created with help from
https://medium.com/@ssaurel/how-to-send-an-email-with-javamail-api-in-android-2fc405441079
 */

import java.security.Security;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

public class MailSender extends javax.mail.Authenticator {

    private String name = null;
    private String recipient = null;
    private String subject = null;
    private String comment = null;

    private SmtpConfiguration smtp;
    private Session session;


    public MailSender(String name, String recipient, String subject, String comment) {
               this.name = name;
               this.recipient = recipient;
               this.subject = subject;
               this.comment = comment;
    }

    static {
        Security.addProvider(new JSSEProvider());
    }

    public void GmailSender() throws Exception {
        smtp = new SmtpConfiguration();

        String host = smtp.getHost();
        String port = smtp.getPort();

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");

        session = Session.getDefaultInstance(props, this);

        sendMail();

    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(smtp.getMail(), smtp.getPassword());
    }

    public synchronized void sendMail() throws Exception {
        MimeMessage message = new MimeMessage(session);
        DataHandler handler = new DataHandler(new ByteArrayDataSource(comment.getBytes(), "text/plain"));
        message.setSender(new InternetAddress(smtp.getMail()));
        message.setSubject(subject);
        message.setDataHandler(handler);

        if (recipient.indexOf(',') > 0)
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        else
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

        Transport.send(message);
    }
}
