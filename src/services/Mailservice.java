package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DbConnection;
import dto.ConfirmationDto;
import dto.PhoneDto;
import utility.SendMail;

@Path("/select")
public class Mailservice {

	DbConnection db1 = new DbConnection();
	SendMail sndml = new SendMail();

	@POST
	@Path("/students")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String sendmail(ConfirmationDto dto) {
		String email = "default";
		PhoneDto phdto;
		String ph = null;
		String name=null;
		ArrayList<PhoneDto> studentlist = new ArrayList<PhoneDto>();
		System.out.println(dto.toString());
		studentlist=dto.getPhlist();
		System.out.println(studentlist);
		String messagebody=dto.getMessage();
		String liststudentnames=null;
		String row=null;
		String messagebodyadmin=null;
		String batch=null;
		try {
			Connection con = db1.getConnection();

			for (int i = 0; i < studentlist.size(); i++) {
				phdto = studentlist.get(i);
				System.out.println(phdto);
				ph = phdto.getPhone();
				System.out.println(ph);
				String stmt = "Select EMAIL_ID,NAME,BATCH from candidate_tbl where PHONE_NO=?";
				PreparedStatement preparedStatement = con.prepareStatement(stmt);
				preparedStatement.setString(1, ph);
				preparedStatement.executeQuery();
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					email = rs.getString(1);
					name=rs.getString(2);
					batch=rs.getString(3);
					System.out.println(email+"  "+ph+"   "+name);
					row=row+"<tr><td>"+ph+"</td><td>"+email+"</td><td>"+name+"</td></tr>";
				}
				System.out.println(email);
				String subject1 = "HUIT:Result of Test";
				String response1 = sndml.sendMail(email, subject1, messagebody);
				System.out.println(response1);
				
				String updatestatus = "UPDATE candidate_tbl SET STATUS=? WHERE PHONE_NO=?";
				PreparedStatement preparedStatement9 = con.prepareStatement(updatestatus);

				preparedStatement9.setString(1, "1");
				preparedStatement9.setString(2, ph);
				
				preparedStatement9.executeUpdate();
				
				String updatestatusTEst = "UPDATE test_tbl SET STATUS='2' WHERE BATCH=?";
				PreparedStatement preparedStatement7 = con.prepareStatement(updatestatusTEst);
				preparedStatement7.setString(1, batch);
				preparedStatement7.executeUpdate();

			}
			
			String subject2 = "HUIT:SelectedStudent List";
			String emailAdmin="hr.incture.demo@gmail.com";
			messagebodyadmin="<table cellpadding='5px'><tr style='padding: 20px' ><td style='text-align: center;'>Phone No</td><td  style='text-align: center;'>Email</td><td  style='text-align: center;'>Name</td></tr>"+row+"</table>";
			String response1 = sndml.sendMail(emailAdmin, subject2, messagebodyadmin);
			System.out.println(response1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getLocalizedMessage();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getLocalizedMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getLocalizedMessage();
		}

		return " mail has been sent to selected student";

	}
}
