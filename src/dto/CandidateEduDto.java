package dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CandidateEduDto {

	public CandidateEduDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	



	public CandidateEduDto(String phone, String boardTenth,
			int percentageTenth, int yearTenth, String boardTwelve,
			int percentageTwelve, int yearTwelve, String collegeUG,
			String specializationUG, int percentageUG, int yearUG,
			String courseUG) {
		super();
		this.phone = phone;
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
		return "CandidateEduDto [phone=" + phone + ", boardTenth=" + boardTenth
				+ ", percentageTenth=" + percentageTenth + ", yearTenth="
				+ yearTenth + ", boardTwelve=" + boardTwelve
				+ ", percentageTwelve=" + percentageTwelve + ", yearTwelve="
				+ yearTwelve + ", collegeUG=" + collegeUG
				+ ", specializationUG=" + specializationUG + ", percentageUG="
				+ percentageUG + ", yearUG=" + yearUG + ", courseUG="
				+ courseUG + "]";
	}


	

	
	
	
	
	

}
