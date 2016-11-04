package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dto.MessageHomePageDto;
import utility.SendMail;

@Path("/message")
public class MessageHomePageService {
	SendMail sm= new SendMail();
	String subject;
	String message;


	@POST
	@Path("/sendmessage")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String submitfeedbackhomepge(MessageHomePageDto dto){
		
		String email="hr.incture.demo@gmail.com";
		subject="FEEDBACK FOR HUIT:SUBJECT:"+dto.getSubject();
		System.out.println(subject);
		message="<b>Feedback From "+dto.getEmail()+"<br>"+"NAME:"+dto.getName()+"<br>"+" MESSAGE:"+"-->"+dto.getMsg();
		sm.sendMail(email, subject, message);
		
		 String email1= dto.getEmail();
		 String subject1="HUIT:NO REPLY";
		 String message1="your feedback/meassge has been sucessfully submitted."+"<br>"+"Thanks for reaching us."+"<br>"+"we will reach you for further information ";
		 sm.sendMail(email1, subject1, message1);
	
	    return "feedback has been sucessfully submited";
		
	}
}
