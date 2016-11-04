package dto;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class QuestionsDto {

	public QuestionsDto() {
		
		// TODO Auto-generated constructor stub
	}
	
	public QuestionsDto(int qID, String question, String option1,
			String option2, String option3, String option4, String correct,
			String category, String difficulty) {
		super();
		this.qID = qID;
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.correct = correct;
		this.category = category;
		this.difficulty = difficulty;
	}



	private int qID;
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String correct;
	private String category;
	private String difficulty;
	public int getqID() {
		return qID;
	}

	public void setqID(int qID) {
		this.qID = qID;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getCorrect() {
		return correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	
	


}
