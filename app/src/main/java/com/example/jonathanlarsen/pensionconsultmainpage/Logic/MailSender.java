package com.example.jonathanlarsen.pensionconsultmainpage.Logic;

/*
This class has been created with help from video
https://www.youtube.com/watch?v=UNPFWCNMJPU
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.jonathanlarsen.pensionconsultmainpage.R;

import java.security.Security;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender extends javax.mail.Authenticator {

    private String name = null;
    private String recipient = null;
    private String subject = null;
    private String comment = null;

    private SmtpConfiguration smtp;
    private Session session = null;

    private ProgressDialog pdialog;
    private Context context;

    static {
        Security.addProvider(new JSSEProvider());
    }

    // constructor
    public MailSender(Context context, String name, String recipient, String subject, String comment) {
        this.context = context;

        this.name = name;
        this.recipient = recipient;
        this.subject = subject;
        this.comment = comment;

        smtp = new SmtpConfiguration();

        Properties props = new Properties();
        props.put("mail.smtp.host", smtp.getHost());
        props.put("mail.smtp.socketFactory.port", smtp.getPort());
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", smtp.getPort());

        session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtp.getMail(), smtp.getPassword());
            }
        });

        // Starting progress dialog
        pdialog = new ProgressDialog(context, R.style.MyProgressDialog);
        pdialog.setMessage("Sender henveldensen");
        pdialog.setCancelable(false);
        pdialog.show();

        // Sending mail
        RetrieveFeedTask task = new RetrieveFeedTask();
        task.execute();
    }

    class RetrieveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                // To client
                String contentToClient = "Du har fået en henvendelse fra " + name + "." + "<p>" +
                        "Kundens e-mail er: " + recipient + "<p>" +
                        "Henvendelsen vedrører: " + subject + "<p>" +
                        "Evt. kommentar fra kunden: " + comment + "<p>" +
                        "Pension Consult";
                String subjectToClient = "Mobil Henvendelse - " + subject;

                Message messageToClient = new MimeMessage(session);
                messageToClient.setFrom(new InternetAddress((smtp.getMail())));
                messageToClient.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient)); // Change this mail to clients mail
                messageToClient.setSubject(subjectToClient);
                messageToClient.setContent(contentToClient, "text/html; charset=utf-8");

                Transport.send(messageToClient);

                // To customer
                String contentToCustomer = "Tak for din henvendelse!" + "<p>" +
                        "Vi vil vende tilbage til dig snarest" + "<p>" +
                        "Venlig hilsen" + "<p>" +
                        "Pension Consult";
                String subjectToCustomer = "Pension Consult";

                Message messageToCustomer = new MimeMessage(session);
                messageToCustomer.setFrom(new InternetAddress((smtp.getMail())));
                messageToCustomer.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
                messageToCustomer.setSubject(subjectToCustomer);
                messageToCustomer.setContent(contentToCustomer, "text/html; charset=utf-8");

                Transport.send(messageToCustomer);
                return "success";

            } catch (MessagingException e) {
                e.printStackTrace();
                return "authentication-failed";
            } catch (Exception e) {
                e.printStackTrace();
                return "failed";
            }
        }

        // stop it when done
        protected void onPostExecute(String result) {
            pdialog.dismiss();

            if (result.matches("success")) {
                // Ved success
                Toast.makeText(context.getApplicationContext(), "Henvendelsen er sendt. Du modtager om et øjeblik en bekræftelse på mail", Toast.LENGTH_LONG).show();
            }else if (result.matches("authentication-failed")) {
                // In case there's an authentication error - for example due to invalid username and or password
                Toast.makeText(context.getApplicationContext(), "(401) Noget gik galt - prøv venligst senere", Toast.LENGTH_LONG).show();
            }else {
                // Catch everything else and give a standard error
                Toast.makeText(context.getApplicationContext(), "Noget gik galt - Prøv venligst senere", Toast.LENGTH_LONG).show();

            }
        }
    }
}
