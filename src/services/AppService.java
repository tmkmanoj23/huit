package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DbConnection;
import dto.AnswerDto;
import dto.AnswerKeyDto;
import dto.CandidateEduDto;
import dto.CandidateGenDto;
import dto.RegisterDto;
import dto.ResponseDto;
import dto.SubmitDto;
import dto.TestDto;
import utility.SendMail;

@Path("/apphome")
public class AppService {
	DbConnection db = new DbConnection();
	ResponseDto rspndto= new ResponseDto();
	private int Cutoff = 0;
	DbConnection db1 = new DbConnection();
	SendMail sndml = new SendMail();
	CandidateGenDto dto = new CandidateGenDto();
	private String message="default";

	
	@POST
	@Path("registerCandidate")
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseDto registerCandidate(RegisterDto dto) {
		
		int count = 0;
		String response1;

		try {
			Connection con = db.getConnection();

			String checkifpresent = "Select * from candidate_tbl where phone_no=?";
			PreparedStatement preparedStatement = con
					.prepareStatement(checkifpresent);
			preparedStatement.setString(1, dto.getPhone());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				count++;
			}

			if (count == 0) {

				Random r = new Random(System.currentTimeMillis());
				int ran = ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));

				SendMail mail = new SendMail();
				String subject = "Your password";
				String message = "<b>Your Password :  </b>" + ran;
				String response = mail.sendMail(dto.getEmail(), subject,
						message);
				if (response.equals("message sent successfully")) {
					String insertNewCandidate = "INSERT INTO candidate_tbl (phone_no,name,email_id,password) values (?,?,?,?)";
					PreparedStatement preparedStatementinsert = con
							.prepareStatement(insertNewCandidate);
					preparedStatementinsert.setString(1, dto.getPhone());
					preparedStatementinsert.setString(2, dto.getName());
					preparedStatementinsert.setString(3, dto.getEmail());
					preparedStatementinsert.setString(4, ran + "");
					// execute insert SQL statement
					preparedStatementinsert.executeUpdate();
					String insertNewCandidateinEDu = "INSERT INTO can_edu_tbl (phone_no) values (?)";
					PreparedStatement preparedStatementinsert1 = con
							.prepareStatement(insertNewCandidateinEDu);
					preparedStatementinsert1.setString(1, dto.getPhone());
					// execute insert SQL statement
					preparedStatementinsert1.executeUpdate();
				} else {
				 response1="Invalid Email Address";
					rspndto.setResponse(response1);
					return rspndto;
				}
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rspndto.setMessage( e.getLocalizedMessage());
			return rspndto;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rspndto.setMessage(e.getLocalizedMessage());
			return rspndto;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rspndto.setMessage(e.getLocalizedMessage());
			return rspndto ;
		}

		if (count == 0) {
		response1=	"Registered";
		rspndto.setResponse(response1);
			return rspndto;
		} else
			
			response1="Already Registered";
		rspndto.setResponse(response1);
			return rspndto ;

	}

	@POST
	@Path("loginCandidate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseDto candidateLogin(CandidateGenDto dto) {
		String ph = "Not Found";
		try {
			Connection con = db.getConnection();

			String checkifpresent = "Select phone_no from candidate_tbl where email_id=? and password=?";
			PreparedStatement preparedStatement = con
					.prepareStatement(checkifpresent);
			preparedStatement.setString(1, dto.getEmail());
			preparedStatement.setString(2, dto.getPassword());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				ph = rs.getString(1);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rspndto.setMessage( e.getLocalizedMessage());
			return rspndto;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rspndto.setMessage(e.getLocalizedMessage());
			return rspndto;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rspndto.setMessage(e.getLocalizedMessage());
			return rspndto ;
		}
		
		rspndto.setResponse(ph);
		return rspndto;
	}
	
	@POST
	@Path("updatecandidateGenInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseDto updateCandidateGenInfo(CandidateGenDto dto) {
		try {
			Connection con = db.getConnection();

			String checkifpresent = "UPDATE candidate_tbl SET  NAME=?, EMAIL_ID=?, ADDRESS=?, GENDER=?, DOB=? WHERE PHONE_NO=?";
			PreparedStatement preparedStatement = con
					.prepareStatement(checkifpresent);

			preparedStatement.setString(1, dto.getName());
			preparedStatement.setString(2, dto.getEmail());
			preparedStatement.setString(3, dto.getAddress());
			preparedStatement.setString(4, dto.getGender());
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = myFormat.format(dto.getDob());
			preparedStatement.setDate(5, java.sql.Date.valueOf(date));
			preparedStatement.setString(6, dto.getPhone());

			preparedStatement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rspndto.setMessage( e.getLocalizedMessage());
			return rspndto;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rspndto.setMessage(e.getLocalizedMessage());
			return rspndto;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rspndto.setMessage(e.getLocalizedMessage());
			return rspndto ;
		}
		String response2="success";
		rspndto.setResponse(response2);
		return rspndto;

		
	}

	
	@POST
	@Path("updtCndtGrdctnInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseDto updateCandidateGrductionInfo(CandidateEduDto dto) {
		try {
			Connection con = db.getConnection();

			String checkifpresent = "UPDATE can_edu_tbl SET COLLAGE_UG=?, SPECIALIZATION=?, PERCENTAGE_UG=? ,YEAR_UG=? WHERE PHONE_NO=?";
			PreparedStatement preparedStatement = con
					.prepareStatement(checkifpresent);

			preparedStatement.setString(1, dto.getCollegeUG());
			preparedStatement.setString(2, dto.getSpecializationUG());
			preparedStatement.setInt(3, dto.getPercentageUG());
			preparedStatement.setInt(4, dto.getYearUG());
			preparedStatement.setString(5, dto.getPhone());

			preparedStatement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rspndto.setMessage( e.getLocalizedMessage());
			return rspndto;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rspndto.setMessage(e.getLocalizedMessage());
			return rspndto;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rspndto.setMessage(e.getLocalizedMessage());
			return rspndto ;
		}
		String response3="success";
		rspndto.setResponse(response3);
		return rspndto;
	}

	@POST
	@Path("updtCndtTenthInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseDto updateCandidateTenthInfo(CandidateEduDto dto) {
		try {
			Connection con = db.getConnection();

			String checkifpresent = "UPDATE can_edu_tbl SET  BORD_TENTH=?, PERCENTAGE_TENTH=?, YEAR_TENTH=? WHERE PHONE_NO=?";
			PreparedStatement preparedStatement = con
					.prepareStatement(checkifpresent);

			preparedStatement.setString(1, dto.getBoardTenth());
			preparedStatement.setInt(2, dto.getPercentageTenth());
			preparedStatement.setInt(3, dto.getYearTenth());
			preparedStatement.setString(4, dto.getPhone());
			preparedStatement.executeUpdate();
			con.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rspndto.setMessage( e.getLocalizedMessage());
			return rspndto;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rspndto.setMessage(e.getLocalizedMessage());
			return rspndto;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rspndto.setMessage(e.getLocalizedMessage());
			return rspndto ;
		}
		String response2="success";
		rspndto.setResponse(response2);
		return rspndto;
	}

	@POST
	@Path("updtCndtTwelveInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseDto updateCandidateTwelveInfo(CandidateEduDto dto) {
		try {
			Connection con = db.getConnection();

			String checkifpresent = "UPDATE can_edu_tbl SET BORD_TWELVE=?, PERCENTAGE_TWELVE=?, YEAR_TWELVE=? WHERE PHONE_NO=?";
			PreparedStatement preparedStatement = con
					.prepareStatement(checkifpresent);

			preparedStatement.setString(1, dto.getBoardTwelve());
			preparedStatement.setInt(2, dto.getPercentageTwelve());
			preparedStatement.setInt(3, dto.getYearTwelve());
			preparedStatement.setString(4, dto.getPhone());
			preparedStatement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rspndto.setMessage( e.getLocalizedMessage());
			return rspndto;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rspndto.setMessage(e.getLocalizedMessage());
			return rspndto;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rspndto.setMessage(e.getLocalizedMessage());
			return rspndto ;
		}
		String response4="success";
		rspndto.setResponse(response4);
		return rspndto;
	}

	@POST
	@Path("/submittest")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseDto submittest(SubmitDto submitDto) {
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

				

			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rspndto.setMessage( e.getLocalizedMessage());
			return rspndto;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rspndto.setMessage(e.getLocalizedMessage());
			return rspndto;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rspndto.setMessage(e.getLocalizedMessage());
			return rspndto ;
		}
		String response5=" test submitted successfully";
		rspndto.setResponse(response5);

		return rspndto;

	}

	@POST
	@Path("submitAnswerKey")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseDto submitAnswers(AnswerKeyDto dto) {
		try {
			Connection con = db.getConnection();
			ArrayList<AnswerDto> answersUser = dto.getAnsId();
			ArrayList<AnswerDto> answersDb = new ArrayList<AnswerDto>();
			AnswerDto dtoans = new AnswerDto();
			int count=0;
			String category="default";
			int noOfquestion=0;

			int in = 0;
			String list = "";
			for (in = 0; in < (answersUser.size() - 1); in++) {

				dtoans = answersUser.get(in);
				list = list + dtoans.getqID() + ",";
			}
			dtoans = answersUser.get(answersUser.size() - 1);
			int qid= dtoans.getqID();
			list = list + dtoans.getqID();
			
			
			String getAnswerKey = "SELECT qID,CORRECT_ANS FROM question_tbl where qid IN ("
					+ list + ")";
			PreparedStatement preparedStatement1 = con
					.prepareStatement(getAnswerKey);
			ResultSet rs1 = preparedStatement1.executeQuery();
			while (rs1.next()) {
				AnswerDto dtoansDb = new AnswerDto();
				dtoansDb.setqID(rs1.getInt(1));
				dtoansDb.setAnswer(rs1.getString(2));
				answersDb.add(dtoansDb);

			}
			
			
			String getCategory = "SELECT CATEGORY FROM question_tbl where QID="+qid;
			PreparedStatement preparedStatement2 = con.prepareStatement(getCategory);
			ResultSet rs2 = preparedStatement2.executeQuery();
			while (rs2.next()) {
				category=rs2.getString(1);
			}
			System.out.println(category);
			
			for (in = 0; in < answersUser.size(); in++) {
				AnswerDto user= new AnswerDto();
				AnswerDto Db= new AnswerDto();
				user=answersUser.get(in);
				Db=answersDb.get(in);
				if(user.getAnswer().equals(Db.getAnswer())){
					count++;
				}
			}
			System.out.println(count);
			
			String noOfquestions = "select NO_OF_"+category+" from candidate_tbl c,test_tbl t where c.BATCH=t.BATCH and c.PHONE_NO="+dto.getPhone();
			PreparedStatement preparedStatement3 = con.prepareStatement(noOfquestions);
			ResultSet rs3 = preparedStatement3.executeQuery();
			while (rs3.next()) {
				noOfquestion=rs3.getInt(1);
			}
			System.out.println(noOfquestion);
			
			int percentage=(int)(((float)count/(float)noOfquestion)*100);
			System.out.println(percentage);
			
			String updateStudentScore = "UPDATE candidate_tbl SET SCORE_"+category+"=? WHERE PHONE_NO=?";
			PreparedStatement preparedStatement5 = con.prepareStatement(updateStudentScore);
            preparedStatement5.setInt(1, percentage);
            preparedStatement5.setString(2, dto.getPhone());
	        preparedStatement5.executeUpdate();
	        con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rspndto.setMessage( e.getLocalizedMessage());
			return rspndto;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rspndto.setMessage(e.getLocalizedMessage());
			return rspndto;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rspndto.setMessage(e.getLocalizedMessage());
			return rspndto ;
		}
		String response6="success";
		rspndto.setResponse(response6);

		return rspndto ;

	}
}
