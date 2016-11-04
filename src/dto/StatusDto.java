package dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StatusDto {
	
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public StatusDto(){
		super();
	}
	
	public StatusDto(String phone){
		super();
		this.phone=phone;
		
	}

}
