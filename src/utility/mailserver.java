package utility;

import java.util.Properties;  
import javax.mail.*;  
import javax.mail.internet.*;  


public class mailserver {
	public static void main(String[] args) {  
		  
		 String to="wasim.anwar8906@gmail.com";//change accordingly  
		  
		  //Get the session object  
		  Properties props = new Properties();  
		  props.put("mail.smtp.host", "smtp.gmail.com");  
		  props.put("mail.smtp.starttls.enable", "true");
		  props.put("mail.smtp.auth", "true");  
		  props.put("mail.smtp.port", "587");  
		   
		  Authenticator auth = new Authenticator() {
	            public PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("hr.incture.demo@gmail.com", "abd124c3");
	            }
	        };
	 
	        Session session = Session.getInstance(props, auth);
		   
		  //compose message  
		  try {  
		   Message message = new MimeMessage(session);  
		   message.setFrom(new InternetAddress("wasim.anwar@incture.com"));//change accordingly  
		   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		   message.setSubject("Hello");  
		   message.setContent("<i>Greetings!</i>", "text/html");;  
		     
		   //send message  
		   Transport.send(message);  
		  
		   System.out.println("message sent successfully");  
		   
		  } catch (MessagingException e) {throw new RuntimeException(e);}  
		   
		 }  
}
