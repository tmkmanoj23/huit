package dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CandidateGenDto {

	public CandidateGenDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public CandidateGenDto(String phone, String name, String email,
			String password, String address, String gender, Date dob,
			int attempt, int score_apti, int score_eng, int score_lr,
			String status, String batch) {
		super();
		this.phone = phone;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.gender = gender;
		this.dob = dob;
		this.attempt = attempt;
		this.score_apti = score_apti;
		this.score_eng = score_eng;
		this.score_lr = score_lr;
		this.status = status;
		this.batch = batch;
	}



	private String phone;
	private String name;
	private String email;
	private String password;
	private String address;
	private String gender;
	private Date dob;
	private int attempt;
	private int score_apti;
	private int score_eng;
	private int score_lr;
	private String status;
	private String batch;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public int getAttempt() {
		return attempt;
	}
	public void setAttempt(int attempt) {
		this.attempt = attempt;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	@Override
	public String toString() {
		return "CandidateGenDto [phone=" + phone + ", name=" + name
				+ ", email=" + email + ", password=" + password + ", address="
				+ address + ", gender=" + gender + ", dob=" + dob
				+ ", attempt=" + attempt + ", score_apti=" + score_apti
				+ ", score_eng=" + score_eng + ", score_lr=" + score_lr
				+ ", status=" + status + ", batch=" + batch + "]";
	}



	
	
	
	
	
}
