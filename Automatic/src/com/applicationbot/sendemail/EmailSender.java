package com.applicationbot.sendemail;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSender {
	
public static boolean sendResume(String to) {
	
      // Sender's email ID needs to be mentioned
      String from = "kharshayadav@yahoo.com";

      final String username = "kharshayadav";//change accordingly
      final String password = "Kishore74";//change accordingly

      // Assuming you are sending email through relay.jangosmtp.net
      String host = "smtp.mail.yahoo.com";

      Properties props = new Properties();
      /*props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "25");*/
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.user", from);
      props.put("mail.smtp.password", password);
      props.put("mail.debug", "false");
      props.put("mail.smtp.port", "587"); //587
      props.put("mail.smtp.auth", "true");

      // Get the Session object.
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
            }
         });

      try {
         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(to));

         // Set Subject: header field
         message.setSubject("SJSU Grad interested in your company");

         // Create the message part
         BodyPart messageBodyPart = new MimeBodyPart();

         // Now set the actual message
         messageBodyPart.setText("\nMy name is Harsha Yadav, I graduated with a Master's degree in Software Engineering at San Jose State University with strong interests in Big Data Analysis, Java, Hadoop, Cloud Computing and Mobile Application Development. \n\nI have one year of work experience in Java. I am actively looking for entry level Software positions. I have been solving problems from leetcode.com, careercup.com since the last 10 months and am always passionate about learning new things and open to exciting challenges. \n\nI understand that even successful engineers at your company might interview multiple times to find the right match, I am confident that I will reach till On-Site interview if I am considered for the position. \n\nI would like to start my career in a company where there are engineering challenges and the huge scope for growth for entry level graduates like me. I would like to connect with you to be considered for Full time Software Developer position. I am attaching my resume for more details. Looking forward to hearing from you.\n\nThanks, \nHarsha.");

         // Create a multipar message
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
         messageBodyPart = new MimeBodyPart();
         String filename = "/Users/harsha/Desktop/HarshayadavK_SoftwareDeveloper.pdf";
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName("HarshayadavK_SoftwareDeveloper.pdf");
         multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
         message.setContent(multipart);

         // Send message
         Transport.send(message);

         System.out.println("Sent message successfully....");
  
      } catch (MessagingException e) {
         e.printStackTrace();
         return false;
      }
      return true;
      
}
}