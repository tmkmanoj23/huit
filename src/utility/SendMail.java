package utility;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	public String sendMail(String to, String subject, String messagebody) {

		String response="Default";
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");

		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("hr.incture.demo@gmail.com",
						"abd124c3");
			}
		};

		Session session = Session.getInstance(props, auth);

		// compose message
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("hr.incture.demo@gmail.com"));// change
																				// accordingly
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message.setSubject(subject);
			message.setContent(messagebody, "text/html");
			;

			// send message
			Transport.send(message);

			response="message sent successfully";

		} catch (MessagingException e) {
			return "Email Invalid";
		}
		return response;
	}
}
