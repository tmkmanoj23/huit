package dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MyScoreDto {
	
	
	public MyScoreDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public MyScoreDto(int nO_OF_APTI, int nO_OF_ENG,
			int nO_OF_LR, int sCORE_APTI, int sCORE_ENG, int sCORE_LR,
			int percentage) {
		super();
		NO_OF_APTI = nO_OF_APTI;
		NO_OF_ENG = nO_OF_ENG;
		NO_OF_LR = nO_OF_LR;
		SCORE_APTI = sCORE_APTI;
		SCORE_ENG = sCORE_ENG;
		SCORE_LR = sCORE_LR;
		this.percentage = percentage;
	}



	private int  NO_OF_APTI;
	private int NO_OF_ENG;
	private int NO_OF_LR;
	private int SCORE_APTI;
	private int SCORE_ENG;
	private int SCORE_LR;
	private int percentage;
	public int getNO_OF_APTI() {
		return NO_OF_APTI;
	}
	public void setNO_OF_APTI(int nO_OF_APTI) {
		NO_OF_APTI = nO_OF_APTI;
	}
	public int getNO_OF_ENG() {
		return NO_OF_ENG;
	}
	public void setNO_OF_ENG(int nO_OF_ENG) {
		NO_OF_ENG = nO_OF_ENG;
	}
	public int getNO_OF_LR() {
		return NO_OF_LR;
	}
	public void setNO_OF_LR(int nO_OF_LR) {
		NO_OF_LR = nO_OF_LR;
	}
	public int getSCORE_APTI() {
		return SCORE_APTI;
	}
	public void setSCORE_APTI(int sCORE_APTI) {
		SCORE_APTI = sCORE_APTI;
	}
	public int getSCORE_ENG() {
		return SCORE_ENG;
	}
	public void setSCORE_ENG(int sCORE_ENG) {
		SCORE_ENG = sCORE_ENG;
	}
	public int getSCORE_LR() {
		return SCORE_LR;
	}
	public void setSCORE_LR(int sCORE_LR) {
		SCORE_LR = sCORE_LR;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	

	


	@Override
	public String toString() {
		return "MyScoreDto [PHONE=" +", NO_OF_APTI=" + NO_OF_APTI
				+ ", NO_OF_ENG=" + NO_OF_ENG + ", NO_OF_LR=" + NO_OF_LR
				+ ", SCORE_APTI=" + SCORE_APTI + ", SCORE_ENG=" + SCORE_ENG
				+ ", SCORE_LR=" + SCORE_LR + ", percentage=" + percentage + "]";
	}



	
	
	

}
