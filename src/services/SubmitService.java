package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import dao.DbConnection;
import dto.CandidateGenDto;
import dto.SubmitDto;
import dto.TestDto;
import utility.SendMail;

@Path("/submit")
public class SubmitService {

	private int Cutoff = 0;
	DbConnection db1 = new DbConnection();
	SendMail sndml = new SendMail();
	CandidateGenDto dto = new CandidateGenDto();

	@POST
	@Path("/submittest")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String submittest(SubmitDto submitDto) {
		String email="default";
		try {
			Connection con = db1.getConnection();
			String stmt = "Select EMAIL_ID from candidate_tbl where PHONE_NO=?";
			PreparedStatement preparedStatement = con.prepareStatement(stmt);
			preparedStatement.setString(1, submitDto.getPhone());
			preparedStatement.executeQuery();
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				 email=rs.getString(1);
			}
			
			
			
			String status = "3";
			TestDto testDto = new TestDto();
			
			String stmt1 = "Select * from test_tbl where PASS_KEY=?";
			PreparedStatement preparedStatement1 = con.prepareStatement(stmt1);
			preparedStatement1.setString(1, submitDto.getPass_key());
			preparedStatement1.executeQuery();
			ResultSet rs1 = preparedStatement1.executeQuery();
			while (rs1.next()) {
				
				testDto.setNoOfApti(rs1.getInt(2));
				testDto.setNoOfEng(rs1.getInt(3));
				testDto.setNoOfLR(rs1.getInt(4));
				testDto.setBatch(rs1.getString(6));
				testDto.setCutOff(rs1.getInt(8));
				Cutoff =testDto.getCutOff() ;
			}
			if (Cutoff == 0) {
				String subject = "HUIT:Status of Test";
				String message = "<b>Your test has been successfully submitted.you will get information about your result very soon. !! All the best!! </b>";
				String response = sndml.sendMail(email, subject, message);
				System.out.println(response);
				String updateintotsttbl = "UPDATE candidate_tbl SET ATTEMPT=?, SCORE_APTI=?, STATUS=?, BATCH=?, SCORE_ENG=?, SCORE_LR=? WHERE PHONE_NO=?";

				PreparedStatement stmt2 = con.prepareStatement(updateintotsttbl);
				stmt2.setInt(1, 1);
				stmt2.setInt(2, submitDto.getScore_apti());
				stmt2.setString(3, status);// 3 means waiting
				stmt2.setString(4, testDto.getBatch());
				stmt2.setInt(5, submitDto.getScore_eng());
				stmt2.setInt(6, submitDto.getScore_lr());
				stmt2.setString(7, submitDto.getPhone());

				stmt2.executeUpdate();
				con.close();

			}

			if (Cutoff != 0) {

				String subject = "HUIT:Status of Test";
				String message = "<b>Your test has been successfully submitted.you will get information about your result very soon. !! All the best!! </b>";
				String response = sndml.sendMail(email, subject, message);
				System.out.println(response);

				float Mrks = submitDto.getScore_apti() + submitDto.getScore_eng() + submitDto.getScore_lr();
				float total = testDto.getNoOfApti() + testDto.getNoOfEng() + testDto.getNoOfLR();
				int cutoffprcntg = (int)((float)(Mrks / total) *100);
				if (cutoffprcntg >= Cutoff) {
					status = "1";
					String subject1 = "HUIT:Result of Test";
					String message1 = "<b>congratulations !!You have been selected for next round!! All the best for next round!! </b>";
					String response1 = sndml.sendMail(email, subject1, message1);
					System.out.println(response1);
				} else {
					status = "2";
					String subject11 = "HUIT:Result of Test";
					String message11 = "<b>You have been not  selected for next round!! All the best for future !! </b>";
					String response11 = sndml.sendMail(email, subject11, message11);
					System.out.println(response11);

				}
				String updateintotsttbl = "UPDATE candidate_tbl SET ATTEMPT=?, SCORE_APTI=?, BATCH=?, STATUS=?,SCORE_ENG=?, SCORE_LR=? WHERE PHONE_NO=?";

				PreparedStatement stmt3 = con.prepareStatement(updateintotsttbl);
				stmt3.setInt(1, 1);
				stmt3.setInt(2, submitDto.getScore_apti());
				stmt3.setString(3, testDto.getBatch());
				stmt3.setString(4, status);
				stmt3.setInt(5, submitDto.getScore_eng());
				stmt3.setInt(6, submitDto.getScore_lr());
				stmt3.setString(7, submitDto.getPhone());

				stmt3.executeUpdate();

				con.close();

			}

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

		return " test submitted successfully";

	}

}