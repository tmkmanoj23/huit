package dto;

import java.util.Date;



import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TestDto {

	public TestDto() {
		// TODO Auto-generated constructor stub
	}

	
	
	public TestDto(String passKey, int noOfApti, int noOfEng, int noOfLR,
			int testTime, String batch, String college, int cutOff, Date dot,
			String difficulty, String status, int time_apti, int time_eng,
			int time_lr) {
		super();
		this.passKey = passKey;
		this.noOfApti = noOfApti;
		this.noOfEng = noOfEng;
		this.noOfLR = noOfLR;
		this.testTime = testTime;
		this.batch = batch;
		this.college = college;
		this.cutOff = cutOff;
		this.dot = dot;
		this.difficulty = difficulty;
		this.status = status;
		this.time_apti = time_apti;
		this.time_eng = time_eng;
		this.time_lr = time_lr;
	}



	private String passKey;
	private int noOfApti;
	private int noOfEng;
	private int noOfLR;
	private int testTime;
	private String batch;
	private String college;
	private int cutOff;
	private Date dot;
	private String difficulty;
	private String status;
	private int time_apti;
	private int time_eng;
	private int time_lr;

	public String getPassKey() {
		return passKey;
	}

	public void setPassKey(String passKey) {
		this.passKey = passKey;
	}

	public int getNoOfApti() {
		return noOfApti;
	}

	public void setNoOfApti(int noOfApti) {
		this.noOfApti = noOfApti;
	}

	public int getNoOfEng() {
		return noOfEng;
	}

	public void setNoOfEng(int noOfEng) {
		this.noOfEng = noOfEng;
	}

	public int getNoOfLR() {
		return noOfLR;
	}

	public void setNoOfLR(int noOfLR) {
		this.noOfLR = noOfLR;
	}

	public int getTestTime() {
		return testTime;
	}

	public void setTestTime(int testTime) {
		this.testTime = testTime;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public int getCutOff() {
		return cutOff;
	}

	public void setCutOff(int cutOff) {
		this.cutOff = cutOff;
	}

	public Date getDot() {
		return dot;
	}

	public void setDot(Date dot) {
		this.dot = dot;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	public int getTime_apti() {
		return time_apti;
	}



	public void setTime_apti(int time_apti) {
		this.time_apti = time_apti;
	}



	public int getTime_eng() {
		return time_eng;
	}



	public void setTime_eng(int time_eng) {
		this.time_eng = time_eng;
	}



	public int getTime_lr() {
		return time_lr;
	}



	public void setTime_lr(int time_lr) {
		this.time_lr = time_lr;
	}



	@Override
	public String toString() {
		return "TestDto [passKey=" + passKey + ", noOfApti=" + noOfApti
				+ ", noOfEng=" + noOfEng + ", noOfLR=" + noOfLR + ", testTime="
				+ testTime + ", batch=" + batch + ", college=" + college
				+ ", cutOff=" + cutOff + ", dot=" + dot + ", difficulty="
				+ difficulty + ", status=" + status + ", time_apti="
				+ time_apti + ", time_eng=" + time_eng + ", time_lr=" + time_lr
				+ "]";
	}



}
