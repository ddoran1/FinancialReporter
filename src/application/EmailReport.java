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

			   String host = "localhost";

			   // Get system properties
			   Properties props = System.getProperties();
			   Enumeration<String> enums = (Enumeration<String>) props.propertyNames();
			    while (enums.hasMoreElements()) {
			      String key = enums.nextElement();
			      String value = props.getProperty(key);
			      System.out.println(key + " : " + value);
			    }

			   // Setup mail server
			   props.setProperty("mail.alexandria", host);

			   // Get the default Session object.
			   Session session = Session.getDefaultInstance(props);

			   try {
				   // Create a default MimeMessage object.
				   MimeMessage message = new MimeMessage(session);

				   // Set From: header field of the header.
				   message.setFrom(new InternetAddress(from));

				   // Set To: header field of the header.
				   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

				   // Set Subject: header field
				   message.setSubject("This is the Subject Line!");

				   // Now set the actual message
				   message.setText("This is actual message");

				   // Send message
				   System.out.println("\n\nSo...we get here...\n\n");
				   Transport.send(message);

				   System.out.println("Sent message successfully....");
				   
			   } catch (MessagingException mex) {
			         
				   mex.printStackTrace();
			   }
		   } catch(Exception e) {
			   e.printStackTrace();
		   }
	   }
	
}
