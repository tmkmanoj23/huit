package dto;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class FeedbackDto {

	public FeedbackDto() {
		// TODO Auto-generated constructor stub
	}

	private String phone;
	private String feedback;

	public FeedbackDto(String phone, String feedback) {
		super();
		this.phone = phone;
		this.feedback = feedback;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "FeedbackDto [phone=" + phone + ", feedback=" + feedback + "]";
	}

}
