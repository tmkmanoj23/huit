package dto;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class AnswerKeyDto {

	private String phone;
	private String category;
	private ArrayList<AnswerDto> ansId;
	
	
	
	public AnswerKeyDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AnswerKeyDto(String phone, String category,
			ArrayList<AnswerDto> ansId) {
		super();
		this.phone = phone;
		this.category = category;
		this.ansId = ansId;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public ArrayList<AnswerDto> getAnsId() {
		return ansId;
	}
	public void setAnsId(ArrayList<AnswerDto> ansId) {
		this.ansId = ansId;
	}
	
	
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "AnswerKeyDto [phone=" + phone + ", category=" + category
				+ ", ansId=" + ansId + "]";
	}

	
	
	 
	
	
	
}
