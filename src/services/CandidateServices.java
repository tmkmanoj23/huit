package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DbConnection;
import dto.AnswerDto;
import dto.AnswerKeyDto;
import dto.CandidateEduDto;
import dto.CandidateGenDto;
import dto.ProfileDto;
import dto.RegisterDto;
import utility.SendMail;

@Path("home")
public class CandidateServices {

	DbConnection db = new DbConnection();

	@POST
	@Path("registerCandidate")
	@Consumes(MediaType.APPLICATION_JSON)
	public String registerCandidate(RegisterDto dto) {

		int count = 0;

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
					String insertNewCandidate = "INSERT INTO candidate_tbl (phone_no,name,email_id,password,attempt) values (?,?,?,?,?)";
					PreparedStatement preparedStatementinsert = con
							.prepareStatement(insertNewCandidate);
					preparedStatementinsert.setString(1, dto.getPhone());
					preparedStatementinsert.setString(2, dto.getName());
					preparedStatementinsert.setString(3, dto.getEmail());
					preparedStatementinsert.setString(4, ran + "");
					preparedStatementinsert.setString(5, "0");
					// execute insert SQL statement
					preparedStatementinsert.executeUpdate();
					String insertNewCandidateinEDu = "INSERT INTO can_edu_tbl (phone_no) values (?)";
					PreparedStatement preparedStatementinsert1 = con
							.prepareStatement(insertNewCandidateinEDu);
					preparedStatementinsert1.setString(1, dto.getPhone());
					// execute insert SQL statement
					preparedStatementinsert1.executeUpdate();
				} else {
					return "Invalid Email Address";
				}
			}
			con.close();
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

		if (count == 0) {
			return "Registered";
		} else
			return "Already Registered";

	}

	@POST
	@Path("loginCandidate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String candidateLogin(CandidateGenDto dto) {
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getLocalizedMessage();
		}
		return ph;
	}

	@POST
	@Path("updateprofile")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateProfile(ProfileDto dto) {
		try {
			Connection con = db.getConnection();

			String checkifpresent = "UPDATE candidate_tbl SET PHONE_NO=?, NAME=?, EMAIL_ID=?, ADDRESS=?, GENDER=?, DOB=?, ATTEMPT=?, SCORE_APTI=?,SCORE_ENG=?,SCORE_LR=?, STATUS=?, BATCH=? WHERE PHONE_NO=?";
			PreparedStatement preparedStatement = con
					.prepareStatement(checkifpresent);
			preparedStatement.setString(1, dto.getNewphone());
			preparedStatement.setString(2, dto.getName());
			preparedStatement.setString(3, dto.getEmail());
			preparedStatement.setString(4, dto.getAddress());
			preparedStatement.setString(5, dto.getGender());
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = myFormat.format(dto.getDob());
			preparedStatement.setDate(6, java.sql.Date.valueOf(date));

			preparedStatement.setInt(7, dto.getAttempt());
			preparedStatement.setInt(8, dto.getScore_apti());
			preparedStatement.setInt(9, dto.getScore_eng());
			preparedStatement.setInt(10, dto.getScore_lr());
			preparedStatement.setString(11, dto.getStatus());
			preparedStatement.setString(12, dto.getBatch());
			preparedStatement.setString(13, dto.getPhone());
			preparedStatement.executeUpdate();
			con.close();
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

		return "success";

	}

	@GET
	@Path("/viewprofile/{ph}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDto viewProfile(@PathParam("ph") String ph) {
		ProfileDto dto = new ProfileDto();
		try {
			Connection con = db.getConnection();
			System.out.println("Phone:" + ph);
			String stmt = "Select * from candidate_tbl where phone_no=?";
			PreparedStatement preparedStatement = con.prepareStatement(stmt);
			preparedStatement.setString(1, ph);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(1) + rs.getString(2));
				dto.setPhone(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setEmail(rs.getString(3));
				dto.setAddress(rs.getString(5));
				dto.setGender(rs.getString(6));

				dto.setDob((java.util.Date) rs.getDate(7));
				dto.setAttempt(rs.getInt(8));
				dto.setScore_apti(rs.getInt(9));
				dto.setStatus(rs.getString(10));
				dto.setBatch(rs.getString(11));
				dto.setScore_eng(rs.getInt(12));
				dto.setScore_lr(rs.getInt(13));

			}

			String stmt1 = "Select * from can_edu_tbl where phone_no=?";
			PreparedStatement preparedStatement1 = con.prepareStatement(stmt1);
			preparedStatement1.setString(1, ph);
			ResultSet rs1 = preparedStatement1.executeQuery();
			while (rs1.next()) {
				System.out.println(rs1.getString(1) + rs1.getString(2));
				// dto.setPhone(rs1.getString(1));
				dto.setBoardTenth(rs1.getString(2));
				dto.setPercentageTenth(rs1.getInt(3));
				dto.setYearTenth(rs1.getInt(4));
				dto.setBoardTwelve(rs1.getString(5));
				dto.setPercentageTwelve(rs1.getInt(6));
				dto.setYearTwelve(rs1.getInt(7));
				dto.setCollegeUG(rs1.getString(8));
				dto.setSpecializationUG(rs1.getString(9));
				dto.setPercentageUG(rs1.getInt(10));
				dto.setYearUG(rs1.getInt(11));
				dto.setCourseUG(rs1.getString(12));

			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dto.setStatus("SQLException");
			return dto;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dto.setStatus("ClassNotFoundException");
			return dto;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dto.setStatus("Exception");
			return dto;
		}
		System.out.println(dto.toString());
		return dto;

	}

	@POST
	@Path("updatecandidateGenInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateCandidateGenInfo(ProfileDto dto) {
		String response="default";
		try {
			Connection con = db.getConnection();

			String checkifpresent = "UPDATE candidate_tbl SET  PHONE_NO=?, NAME=?, ADDRESS=?, GENDER=?, DOB=? WHERE PHONE_NO=?";
			PreparedStatement preparedStatement = con
					.prepareStatement(checkifpresent);
			preparedStatement.setString(1, dto.getNewphone());
			preparedStatement.setString(2, dto.getName());
			preparedStatement.setString(3, dto.getAddress());
			preparedStatement.setString(4, dto.getGender());
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = myFormat.format(dto.getDob());
			preparedStatement.setDate(5, java.sql.Date.valueOf(date));
			preparedStatement.setString(6, dto.getPhone());
			preparedStatement.executeUpdate();
			String upadeteEduTbl = "UPDATE can_edu_tbl SET  PHONE_NO=? WHERE PHONE_NO=?";
			PreparedStatement preparedStatement1 = con.prepareStatement(upadeteEduTbl);
			preparedStatement1.setString(1, dto.getNewphone());
			preparedStatement1.setString(2, dto.getPhone());
			preparedStatement1.executeUpdate();
			response="success";
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response=e.getLocalizedMessage();
			return response;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response=e.getLocalizedMessage();
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response=e.getLocalizedMessage();
			return response;
		}

		return response;
	}

	@POST
	@Path("updtCndtGrdctnInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateCandidateGrductionInfo(CandidateEduDto dto) {
		String response="default";
		try {
			Connection con = db.getConnection();

			String checkifpresent = "UPDATE can_edu_tbl SET COLLAGE_UG=?, SPECIALIZATION=?, PERCENTAGE_UG=? ,YEAR_UG=?,COURSE=? WHERE PHONE_NO=?";
			PreparedStatement preparedStatement = con
					.prepareStatement(checkifpresent);

			preparedStatement.setString(1, dto.getCollegeUG());
			preparedStatement.setString(2, dto.getSpecializationUG());
			preparedStatement.setInt(3, dto.getPercentageUG());
			preparedStatement.setInt(4, dto.getYearUG());
			preparedStatement.setString(5, dto.getCourseUG());
			preparedStatement.setString(6, dto.getPhone());

			preparedStatement.executeUpdate();
			response="success";
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response=e.getLocalizedMessage();
			return response;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response=e.getLocalizedMessage();
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response=e.getLocalizedMessage();
			return response;
		}

		return response;

	}

	@POST
	@Path("updtCndtTenthInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateCandidateTenthInfo(CandidateEduDto dto) {
		String response= "default";
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
			response="success";
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response=e.getLocalizedMessage();
			return response;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response=e.getLocalizedMessage();
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response=e.getLocalizedMessage();
			return response;
		}

		return response;

	}

	@POST
	@Path("updtCndtTwelveInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateCandidateTwelveInfo(CandidateEduDto dto) {
		String response="default";
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
			response="success";
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response=e.getLocalizedMessage();
			return response;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response=e.getLocalizedMessage();
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response=e.getLocalizedMessage();
			return response;
		}

		return response;

	}

	@POST
	@Path("submitAnswerKey")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String submitAnswers(AnswerKeyDto dto) {
		System.out.println(dto.toString());
		
		try {
			Connection con = db.getConnection();
			ArrayList<AnswerDto> answersUser = dto.getAnsId();
			System.out.println(answersUser);
			if(answersUser!=null){
			ArrayList<AnswerDto> answersDb = new ArrayList<AnswerDto>();
			AnswerDto dtoans = new AnswerDto();
			int count=0;
			int noOfquestion=0;
			String passkey1="default";
			int in = 0;
			String list = "";
			for (in = 0; in < (answersUser.size() - 1); in++) {

				dtoans = answersUser.get(in);
				list = list + dtoans.getqID() + ",";
			}
			dtoans = answersUser.get(answersUser.size() - 1);
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
			System.out.println(answersDb);
			
			
			System.out.println("answersUser:"+answersUser);
			System.out.println("answersDb:"+answersDb);
			int j=0;
			for (in = 0; in < answersUser.size(); in++){
				AnswerDto user= new AnswerDto();
				AnswerDto Db= new AnswerDto();
				user=answersUser.get(in);
				for(j = 0; j < answersDb.size(); j++){
					Db=answersDb.get(j);
					if(user.getqID()==Db.getqID()){
						if(user.getAnswer().equals(Db.getAnswer().trim()))
						{
							count++;
						}
					}
				}
			}		
			System.out.println(count);
			
			String noOfquestions = "select NO_OF_"+dto.getCategory()+",pass_key from candidate_tbl c,test_tbl t where c.BATCH=t.BATCH and c.PHONE_NO="+dto.getPhone();
			System.out.println(noOfquestions);
			PreparedStatement preparedStatement3 = con.prepareStatement(noOfquestions);
			ResultSet rs3 = preparedStatement3.executeQuery();
			while (rs3.next()) {
				noOfquestion=rs3.getInt(1);
				passkey1=rs3.getString(2);
			}
			
			int percentage=(int)(((float)count/(float)noOfquestion)*100);
			System.out.println(percentage);
			
			String updateStudentScore = "UPDATE candidate_tbl SET SCORE_"+dto.getCategory()+"=? WHERE PHONE_NO=?";
			PreparedStatement preparedStatement5 = con.prepareStatement(updateStudentScore);
            preparedStatement5.setInt(1, percentage);
            preparedStatement5.setString(2, dto.getPhone());
	        preparedStatement5.executeUpdate();
	        
	        String updateTestStatus = "UPDATE test_tbl SET `STATUS`='1' WHERE pass_key=?";
			PreparedStatement preparedStatement6 = con.prepareStatement(updateTestStatus);
            preparedStatement6.setString(1, passkey1);
	        preparedStatement6.executeUpdate();
			}
			else{
				String updateStudentScorewhenNull = "UPDATE candidate_tbl SET SCORE_"+dto.getCategory()+"=0 WHERE PHONE_NO=?";
				PreparedStatement preparedStatement6 = con.prepareStatement(updateStudentScorewhenNull);
	            preparedStatement6.setString(1, dto.getPhone());
		        preparedStatement6.executeUpdate();
			}
			con.close();
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

		return "success";

	}
	
	@GET
	@Path("/getPhone/{email}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getphone(@PathParam("email") String email) {
		String phone="Default";
		int count=0;
		try {
			Connection con = db.getConnection();
			String stmt = "Select phone_no from candidate_tbl where email_id=?";
			PreparedStatement preparedStatement = con.prepareStatement(stmt);
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				count++;
				phone=rs.getString(1);
				
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		if(count>0)
		return phone;
		else
			return "Not Found";

	}
	
	@GET
	@Path("/getPassword/{email}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getPassword(@PathParam("email") String email) {
			String password="default";
		try {
			Connection con = db.getConnection();
			String stmt = "Select password from candidate_tbl where email_id=?";
			PreparedStatement preparedStatement = con.prepareStatement(stmt);
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				password=rs.getString(1);
				 SendMail mail=new SendMail();
				 String message="Your Password is: " + password;
				 mail.sendMail(email, "Your Password", message);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;

	}

}