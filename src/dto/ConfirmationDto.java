package dto;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ConfirmationDto {
	
	
	
	
public ConfirmationDto(String message, ArrayList<PhoneDto> phlist) {
		super();
		this.message = message;
		this.phlist = phlist;
	}
public ConfirmationDto() {
		super();
		// TODO Auto-generated constructor stub
	}
private String message;
private ArrayList<PhoneDto>  phlist;
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public ArrayList<PhoneDto> getPhlist() {
	return phlist;
}
public void setPhlist(ArrayList<PhoneDto> phlist) {
	this.phlist = phlist;
}
@Override
public String toString() {
	return "ConformationDto [message=" + message + ", phlist=" + phlist + "]";
}

}
