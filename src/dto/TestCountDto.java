package dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TestCountDto {
	public TestCountDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	private int count_current_month;
	private int count_next_month;
	private int count_today;
	public TestCountDto(int count_current_month, int count_next_month, int count_today) {
		super();
		this.count_current_month = count_current_month;
		this.count_next_month = count_next_month;
		this.count_today = count_today;
	}
	
	public int getCount_current_month() {
		return count_current_month;
	}
	public void setCount_current_month(int count_current_month) {
		this.count_current_month = count_current_month;
	}
	public int getCount_next_month() {
		return count_next_month;
	}
	public void setCount_next_month(int count_next_month) {
		this.count_next_month = count_next_month;
	}
	public int getCount_today() {
		return count_today;
	}
	public void setCount_today(int count_today) {
		this.count_today = count_today;
	}
	@Override
	public String toString() {
		return "TestCountDto [count_current_month=" + count_current_month + ", count_next_month=" + count_next_month
				+ ", count_today=" + count_today + "]";
	}
	
	

}
