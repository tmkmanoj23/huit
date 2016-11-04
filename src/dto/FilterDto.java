package dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FilterDto {

	public FilterDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	


public FilterDto(String passkey, int cutoff_eng, int cutoff_lr,
			int cutoff_apti, int cutoff_total, int studenttotal) {
		super();
		this.passkey = passkey;
		this.cutoff_eng = cutoff_eng;
		this.cutoff_lr = cutoff_lr;
		this.cutoff_apti = cutoff_apti;
		this.cutoff_total = cutoff_total;
		this.studenttotal = studenttotal;
	}




private String passkey;
private int cutoff_eng;
private int cutoff_lr;
private int cutoff_apti;
private int cutoff_total;
private int studenttotal;
public String getPasskey() {
	return passkey;
}
public void setPasskey(String passkey) {
	this.passkey = passkey;
}
public int getCutoff_eng() {
	return cutoff_eng;
}
public void setCutoff_eng(int cutoff_eng) {
	this.cutoff_eng = cutoff_eng;
}
public int getCutoff_lr() {
	return cutoff_lr;
}
public void setCutoff_lr(int cutoff_lr) {
	this.cutoff_lr = cutoff_lr;
}
public int getCutoff_apti() {
	return cutoff_apti;
}
public void setCutoff_apti(int cutoff_apti) {
	this.cutoff_apti = cutoff_apti;
}
public int getCutoff_total() {
	return cutoff_total;
}
public void setCutoff_total(int cutoff_total) {
	this.cutoff_total = cutoff_total;
}




public int getStudenttotal() {
	return studenttotal;
}




public void setStudenttotal(int studenttotal) {
	this.studenttotal = studenttotal;
}




@Override
public String toString() {
	return "FilterDto [passkey=" + passkey + ", cutoff_eng=" + cutoff_eng
			+ ", cutoff_lr=" + cutoff_lr + ", cutoff_apti=" + cutoff_apti
			+ ", cutoff_total=" + cutoff_total + ", studenttotal="
			+ studenttotal + "]";
}



}
