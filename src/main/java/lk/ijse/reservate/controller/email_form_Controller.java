package lk.ijse.reservate.controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.AbstractList;
import java.util.Properties;

public class email_form_Controller {

    @FXML
    private AnchorPane reservationPane;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtSubject;

    @FXML
    private JFXTextArea txtMessage;

    @FXML
    protected Label lblStatus;


    @FXML
    void btnSendOnAction(ActionEvent event) {
        System.out.println("Start");
        lblStatus.setStyle("-fx-text-fill: black");
        lblStatus.setText("sending...");
        Mail mail = new Mail(); //creating an instance of Mail class
        mail.setMsg(txtMessage.getText());//email message
        mail.setTo(txtEmail.getText()); //receiver's mail
        mail.setSubject(txtSubject.getText()); //email subject

        Thread thread = new Thread(mail);
        thread.start();

        System.out.println("end");

    }

    public class Mail implements Runnable{
        private String msg;
        private String to;
        private String subject;
        public void setMsg(String msg) {
            this.msg = msg;
        }
        public void setTo(String to) {
            this.to = to;
        }
        public void setSubject(String subject) {
            this.subject = subject;
        }

        public boolean outMail() throws MessagingException {
            String from = "soysaquarium@gmail.com"; //sender's email address
            String host = "localhost";

            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", 587);
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("soysaquarium@gmail.com", "Jaeauxxlmpjptiky");  // have to change some settings in SMTP
                }
            });

            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(this.subject);
            mimeMessage.setText(this.msg);
            Transport.send(mimeMessage);
            return true;
        }
        public void run() {
            if (msg != null) {
                try {
                    boolean b = outMail();
                    if (b) {
                        Platform.runLater(() -> {
                            lblStatus.setStyle("-fx-text-fill: green");
                            lblStatus.setText("Sent Successfully !");
                        });
                    }
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Platform.runLater(() -> {
                    lblStatus.setStyle("-fx-text-fill: red");
                    lblStatus.setText("not Sent!");
                });
            }
        }


//        public void run() {
//            if (msg != null) {
//                try {
//                    boolean b = outMail();
//                    if(b){
//                        lblStatus.setStyle("-fx-text-fill: green");
//                        lblStatus.setText("Sent Successfully !");
//                    }
//                } catch (MessagingException e) {
//                    throw new RuntimeException(e);
//                }
//            } else {
//                lblStatus.setStyle("-fx-text-fill: red");
//                lblStatus.setText("not Sent!");
//            }
//        }
    }


}
