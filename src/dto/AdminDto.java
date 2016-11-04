package dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AdminDto {
	
	
	
public AdminDto() {
		super();
		// TODO Auto-generated constructor stub
	}
public AdminDto(String phone, String email, String password) {
	super();
	this.phone = phone;
	this.email = email;
	this.password = password;
}
private String phone;
private String email;
private String password;
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
@Override
public String toString() {
	return "AdminDto [phone=" + phone + ", email=" + email + ", password=" + password + "]";
}


}
