package dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RegisterDto {

	public RegisterDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RegisterDto(String name, String email, String phone) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	private String name;
	private String email;
	private String phone;
	
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

	
	@Override
	public String toString() {
		return "RegisterDto [name=" + name + ", email=" + email + ", phone="
				+ phone + "]";
	}
	
}
