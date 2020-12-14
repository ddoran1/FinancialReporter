package application;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.io.*;

public class EmailReport {

	   public static void main() throws UnknownHostException, IOException {  
		   
		   
		   try {		 
			   String to = "davidwdoran@gmail.com";
			   String from = "davidwdoran@mail.alexandria";

			   // Get system properties
			   Properties props = System.getProperties();
			   Enumeration<String> enums = (Enumeration<String>) props.propertyNames();
			    while (enums.hasMoreElements()) {
			      String key = enums.nextElement();
			      String value = props.getProperty(key);
			      System.out.println(key + " : " + value);
			    }

			   // Setup mail server
			   props.setProperty("mail.alexandria", "localhost");

			   Session session = Session.getDefaultInstance(props);

			   try {
				   MimeMessage message = new MimeMessage(session);
				   message.setFrom(new InternetAddress(from));
				   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				   message.setSubject("Subject Line!");
				   message.setText("message");

				   Transport.send(message);

				   System.out.println("Message sent");				   
			   } 
			   catch (MessagingException mex) {
			         
				   mex.printStackTrace();
			   }
		   } catch(Exception e) {
			   e.printStackTrace();
		   }
	   }
	
}
