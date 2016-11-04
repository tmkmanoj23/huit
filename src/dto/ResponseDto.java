package dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseDto {
	
	
	public ResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResponseDto(String response, String message) {
		super();
		this.response = response;
		this.message = message;
	}
	private String response;
	private String message;
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ResponseDto [response=" + response + ", message=" + message + "]";
	}
	

}
