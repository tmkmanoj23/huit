package dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PhoneDto {
	
	public PhoneDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PhoneDto(String phone) {
		super();
		this.phone = phone;
	}

	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "PhoneDto [phone=" + phone + "]";
	}

}
