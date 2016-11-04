package dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SubmitDto {
	public SubmitDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	


	public SubmitDto(String pass_key, int score_apti, int score_eng,
			int score_lr, String phone) {
		super();
		this.pass_key = pass_key;
		this.score_apti = score_apti;
		this.score_eng = score_eng;
		this.score_lr = score_lr;
		this.phone = phone;
	}




	private String pass_key;
	private int score_apti;
	private int score_eng;
	private int score_lr;
	private String phone;
	
	public String getPass_key() {
		return pass_key;
	}




	public void setPass_key(String pass_key) {
		this.pass_key = pass_key;
	}




	public int getScore_apti() {
		return score_apti;
	}




	public void setScore_apti(int score_apti) {
		this.score_apti = score_apti;
	}
	public int getScore_eng() {
		return score_eng;
	}
	public void setScore_eng(int score_eng) {
		this.score_eng = score_eng;
	}
	public int getScore_lr() {
		return score_lr;
	}
	public void setScore_lr(int score_lr) {
		this.score_lr = score_lr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}




	@Override
	public String toString() {
		return "SubmitDto [pass_key=" + pass_key + ", score_apti=" + score_apti
				+ ", score_eng=" + score_eng + ", score_lr=" + score_lr
				+ ", phone=" + phone + "]";
	}
	
	
	
}
