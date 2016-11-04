package dto;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class AnswerDto {
	private int qID;
	private String answer;
	
	
	public AnswerDto() {
		super();
		// TODO Auto-generated constructor stub
	}



	public AnswerDto(int qID, String answer) {
		super();
		this.qID = qID;
		this.answer = answer;
	}
	
	
	
	public int getqID() {
		return qID;
	}



	public void setqID(int qID) {
		this.qID = qID;
	}



	public String getAnswer() {
		return answer;
	}



	public void setAnswer(String answer) {
		this.answer = answer;
	}



	@Override
	public String toString() {
		return "AnswerDto [qID=" + qID + ", answer=" + answer + "]";
	}
	
	
	
}
