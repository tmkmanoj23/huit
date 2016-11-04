package dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StudentCoutDto {
	
	public StudentCoutDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public StudentCoutDto(int selected_student, int unselected_student) {
		super();
		this.selected_student = selected_student;
		this.unselected_student = unselected_student;
		
	}

	private int selected_student;
	private int unselected_student;
	
	public int getSelected_student() {
		return selected_student;
	}

	public void setSelected_student(int selected_student) {
		this.selected_student = selected_student;
	}

	public int getUnselected_student() {
		return unselected_student;
	}

	public void setUnselected_student(int unselected_student) {
		this.unselected_student = unselected_student;
	}

	@Override
	public String toString() {
		return "StudentCoutDto [selected_student=" + selected_student + ", unselected_student=" + unselected_student
				+ "]";
	}

	
	

}
