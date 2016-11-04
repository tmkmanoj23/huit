package dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MessageHomePageDto {
	
	
public MessageHomePageDto() {
		super();
		// TODO Auto-generated constructor stub
	}

public MessageHomePageDto(String name, String email, String subject, String msg) {
	super();
	this.name = name;
	this.email = email;
	this.subject = subject;
	this.msg = msg;
}

private String name;
private String email;
private String subject;
private String msg;
public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getSubject() {
	return subject;
}

public void setSubject(String subject) {
	this.subject = subject;
}

public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}

@Override
public String toString() {
	return "FeedbackHomePageDto [name=" + name + ", email=" + email + ", subject=" + subject + ", msg=" + msg + "]";
}


}
