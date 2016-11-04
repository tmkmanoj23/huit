package dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProfileDto {

	public ProfileDto() {
		// TODO Auto-generated constructor stub
	}
	

	public ProfileDto(String phone, String name, String email, String address,
			String gender, Date dob, int attempt, int score_apti,
			int score_eng, int score_lr, String status, String batch,
			String newphone, String boardTenth, int percentageTenth,
			int yearTenth, String boardTwelve, int percentageTwelve,
			int yearTwelve, String collegeUG, String specializationUG,
			int percentageUG, int yearUG, String courseUG) {
		super();
		this.phone = phone;
		this.name = name;
		this.email = email;
		this.address = address;
		this.gender = gender;
		this.dob = dob;
		this.attempt = attempt;
		this.score_apti = score_apti;
		this.score_eng = score_eng;
		this.score_lr = score_lr;
		this.status = status;
		this.batch = batch;
		this.newphone = newphone;
		this.boardTenth = boardTenth;
		this.percentageTenth = percentageTenth;
		this.yearTenth = yearTenth;
		this.boardTwelve = boardTwelve;
		this.percentageTwelve = percentageTwelve;
		this.yearTwelve = yearTwelve;
		this.collegeUG = collegeUG;
		this.specializationUG = specializationUG;
		this.percentageUG = percentageUG;
		this.yearUG = yearUG;
		this.courseUG = courseUG;
	}


	private String phone;
	private String name;
	private String email;
	private String address;
	private String gender;
	private Date dob;
	private int attempt;
	private int score_apti;
	private int score_eng;
	private int score_lr;
	private String status;
	private String batch;
	private String newphone;
	private String boardTenth;
	private int percentageTenth;
	private int yearTenth;
	
	private String boardTwelve;
	private int percentageTwelve;
	private int yearTwelve;
	
	private String collegeUG;
	private String specializationUG;
	private int percentageUG;
	private int yearUG;
	private String courseUG;
	
	
	
	



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
	


	public String getNewphone() {
		return newphone;
	}







	public void setNewphone(String newphone) {
		this.newphone = newphone;
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



	public String getBoardTenth() {
		return boardTenth;
	}



	public void setBoardTenth(String boardTenth) {
		this.boardTenth = boardTenth;
	}



	public int getPercentageTenth() {
		return percentageTenth;
	}



	public void setPercentageTenth(int percentageTenth) {
		this.percentageTenth = percentageTenth;
	}



	public int getYearTenth() {
		return yearTenth;
	}



	public void setYearTenth(int yearTenth) {
		this.yearTenth = yearTenth;
	}



	public String getBoardTwelve() {
		return boardTwelve;
	}



	public void setBoardTwelve(String boardTwelve) {
		this.boardTwelve = boardTwelve;
	}



	public int getPercentageTwelve() {
		return percentageTwelve;
	}



	public void setPercentageTwelve(int percentageTwelve) {
		this.percentageTwelve = percentageTwelve;
	}



	public int getYearTwelve() {
		return yearTwelve;
	}



	public void setYearTwelve(int yearTwelve) {
		this.yearTwelve = yearTwelve;
	}



	public String getCollegeUG() {
		return collegeUG;
	}



	public void setCollegeUG(String collegeUG) {
		this.collegeUG = collegeUG;
	}



	public String getSpecializationUG() {
		return specializationUG;
	}



	public void setSpecializationUG(String specializationUG) {
		this.specializationUG = specializationUG;
	}



	



	public int getPercentageUG() {
		return percentageUG;
	}







	public void setPercentageUG(int percentageUG) {
		this.percentageUG = percentageUG;
	}







	public int getYearUG() {
		return yearUG;
	}



	public void setYearUG(int yearUG) {
		this.yearUG = yearUG;
	}
	
	


	public String getCourseUG() {
		return courseUG;
	}


	public void setCourseUG(String courseUG) {
		this.courseUG = courseUG;
	}


	@Override
	public String toString() {
		return "ProfileDto [phone=" + phone + ", name=" + name + ", email="
				+ email + ", address=" + address + ", gender=" + gender
				+ ", dob=" + dob + ", attempt=" + attempt + ", score_apti="
				+ score_apti + ", score_eng=" + score_eng + ", score_lr="
				+ score_lr + ", status=" + status + ", batch=" + batch
				+ ", newphone=" + newphone + ", boardTenth=" + boardTenth
				+ ", percentageTenth=" + percentageTenth + ", yearTenth="
				+ yearTenth + ", boardTwelve=" + boardTwelve
				+ ", percentageTwelve=" + percentageTwelve + ", yearTwelve="
				+ yearTwelve + ", collegeUG=" + collegeUG
				+ ", specializationUG=" + specializationUG + ", percentageUG="
				+ percentageUG + ", yearUG=" + yearUG + ", courseUG="
				+ courseUG + "]";
	}







	







	


	


	
	
	
	
}
