package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DbConnection;
import dto.QuestionPaperDto;
import dto.QuestionsDto;
import dto.TestDto;

@Path("/questions")
public class QuestionsServices {

	DbConnection db = new DbConnection();

	@GET
	@Path("/getTest/{passkey}/{ph}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<QuestionPaperDto> getQuestions(@PathParam("passkey") String passkey, @PathParam("ph") String ph) {

		int attempt = -1;
		int count = 2;
		TestDto testDto = new TestDto();
		ArrayList<QuestionPaperDto> questionsSet = new ArrayList<QuestionPaperDto>();
		try {
			Connection con = db.getConnection();
				String stmt1 = "Select * from test_tbl where pass_key=?";
				PreparedStatement preparedStatement1 = con
						.prepareStatement(stmt1);
				preparedStatement1.setString(1, passkey);
				preparedStatement1.executeQuery();
				ResultSet rs1 = preparedStatement1.executeQuery();
				while (rs1.next()) {

					testDto.setNoOfApti(rs1.getInt(2));
					testDto.setNoOfEng(rs1.getInt(3));
					testDto.setNoOfLR(rs1.getInt(4));
					testDto.setTestTime(rs1.getInt(5));
					testDto.setBatch(rs1.getString(6));
					testDto.setCollege(rs1.getString(7));
					testDto.setCutOff(rs1.getInt(8));
					testDto.setDifficulty(rs1.getString(10));
					count++;
				}
				if (count != 2) {
					String getrandomquestionsApti = "SELECT * FROM question_tbl where category=? and type=? ORDER BY RAND() LIMIT ?";
					PreparedStatement preparedStatement2 = con
							.prepareStatement(getrandomquestionsApti);
					preparedStatement2.setString(1, "Apti");
					preparedStatement2.setString(2, testDto.getDifficulty());
					preparedStatement2.setInt(3, testDto.getNoOfApti());
					preparedStatement2.executeQuery();
					ResultSet rs2 = preparedStatement2.executeQuery();
					while (rs2.next()) {
						QuestionPaperDto questions = new QuestionPaperDto(
								rs2.getInt(1), rs2.getString(2),
								rs2.getString(3), rs2.getString(4),
								rs2.getString(5), rs2.getString(6));
								
								

						questionsSet.add(questions);

					}
				} else {
					QuestionPaperDto ques = new QuestionPaperDto();
					ques.setQuestion("PassKey Incorrect");
					questionsSet.add(ques);
				}
				con.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return questionsSet;
	}
	
	
	
	@GET
	@Path("/getTestEng/{passkey}/{ph}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<QuestionPaperDto> getQuestionsEng(@PathParam("passkey") String passkey, @PathParam("ph") String ph) {

		int attempt = -1;
		int count = 2;
		TestDto testDto = new TestDto();
		ArrayList<QuestionPaperDto> questionsSet = new ArrayList<QuestionPaperDto>();
		try {
			Connection con = db.getConnection();
				String stmt1 = "Select * from test_tbl where pass_key=?";
				PreparedStatement preparedStatement1 = con
						.prepareStatement(stmt1);
				preparedStatement1.setString(1, passkey);
				preparedStatement1.executeQuery();
				ResultSet rs1 = preparedStatement1.executeQuery();
				while (rs1.next()) {

					testDto.setNoOfApti(rs1.getInt(2));
					testDto.setNoOfEng(rs1.getInt(3));
					testDto.setNoOfLR(rs1.getInt(4));
					testDto.setTestTime(rs1.getInt(5));
					testDto.setBatch(rs1.getString(6));
					testDto.setCollege(rs1.getString(7));
					testDto.setCutOff(rs1.getInt(8));
					testDto.setDifficulty(rs1.getString(10));
					count++;
				}
				if (count != 2) {
					String getrandomquestionsApti = "SELECT * FROM question_tbl where category=? and type=? ORDER BY RAND() LIMIT ?";
					PreparedStatement preparedStatement2 = con
							.prepareStatement(getrandomquestionsApti);
					preparedStatement2.setString(1, "Eng");
					preparedStatement2.setString(2, testDto.getDifficulty());
					preparedStatement2.setInt(3, testDto.getNoOfEng());
					preparedStatement2.executeQuery();
					ResultSet rs2 = preparedStatement2.executeQuery();
					while (rs2.next()) {
						QuestionPaperDto questions = new QuestionPaperDto(
								rs2.getInt(1), rs2.getString(2),
								rs2.getString(3), rs2.getString(4),
								rs2.getString(5), rs2.getString(6));
								
								

						questionsSet.add(questions);

					}
				} else {
					QuestionPaperDto ques = new QuestionPaperDto();
					ques.setQuestion("PassKey Incorrect");
					questionsSet.add(ques);
				}
				con.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return questionsSet;
	}
	
	@GET
	@Path("/getTestLr/{passkey}/{ph}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<QuestionPaperDto> getQuestionsLR(@PathParam("passkey") String passkey, @PathParam("ph") String ph) {

		int attempt = -1;
		int count = 2;
		TestDto testDto = new TestDto();
		ArrayList<QuestionPaperDto> questionsSet = new ArrayList<QuestionPaperDto>();
		try {
			Connection con = db.getConnection();
				String stmt1 = "Select * from test_tbl where pass_key=?";
				PreparedStatement preparedStatement1 = con
						.prepareStatement(stmt1);
				preparedStatement1.setString(1, passkey);
				preparedStatement1.executeQuery();
				ResultSet rs1 = preparedStatement1.executeQuery();
				while (rs1.next()) {

					testDto.setNoOfApti(rs1.getInt(2));
					testDto.setNoOfEng(rs1.getInt(3));
					testDto.setNoOfLR(rs1.getInt(4));
					testDto.setTestTime(rs1.getInt(5));
					testDto.setBatch(rs1.getString(6));
					testDto.setCollege(rs1.getString(7));
					testDto.setCutOff(rs1.getInt(8));
					testDto.setDifficulty(rs1.getString(10));
					count++;
				}
				if (count != 2) {
					String getrandomquestionsApti = "SELECT * FROM question_tbl where category=? and type=? ORDER BY RAND() LIMIT ?";
					PreparedStatement preparedStatement2 = con
							.prepareStatement(getrandomquestionsApti);
					preparedStatement2.setString(1, "Lr");
					preparedStatement2.setString(2, testDto.getDifficulty());
					preparedStatement2.setInt(3, testDto.getNoOfLR());
					preparedStatement2.executeQuery();
					ResultSet rs2 = preparedStatement2.executeQuery();
					while (rs2.next()) {
						QuestionPaperDto questions = new QuestionPaperDto(
								rs2.getInt(1), rs2.getString(2),
								rs2.getString(3), rs2.getString(4),
								rs2.getString(5), rs2.getString(6));
								
								

						questionsSet.add(questions);

					}
				} else {
					QuestionPaperDto ques = new QuestionPaperDto();
					ques.setQuestion("PassKey Incorrect");
					questionsSet.add(ques);
				}
				con.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return questionsSet;
	}
	
	
	@POST
	@Path("/createQuestion")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createQues(QuestionsDto questiondto) {
	
		try {
			Connection conn = db.getConnection();
			String insertintotbl = "INSERT INTO `question_tbl` (`QUESTION`, `OPTION_1`, `OPTION_2`, `OPTION_3`, `OPTION_4`, `CORRECT_ANS`, `CATEGORY`, `TYPE`) VALUES (?,?,?,?,?,?,?,?);";
			PreparedStatement stmt = conn.prepareStatement(insertintotbl);
			
			stmt.setString(1, questiondto.getQuestion());
			stmt.setString(2, questiondto.getOption1());
			stmt.setString(3, questiondto.getOption2());
			stmt.setString(4, questiondto.getOption3());
			stmt.setString(5, questiondto.getOption4());
			stmt.setString(6, questiondto.getCorrect());
			stmt.setString(7, questiondto.getCategory());
			stmt.setString(8, questiondto.getDifficulty());
	        stmt.executeUpdate();
         conn.close();
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

		return "Question Submitted Successfully";
	}
	@POST
	@Path("/createMultipleQuestion")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createMultiQues(ArrayList<QuestionsDto> questiondtoall) {
		String response="default";
		try {
			Connection conn = db.getConnection();
			for (int i = 0; i < questiondtoall.size(); i++)
			{
				QuestionsDto questiondto = new QuestionsDto();
				questiondto = questiondtoall.get(i);
				
				String insertintotbl = "INSERT INTO `question_tbl` (`QUESTION`, `OPTION_1`, `OPTION_2`, `OPTION_3`, `OPTION_4`, `CORRECT_ANS`, `CATEGORY`, `TYPE`) VALUES (?,?,?,?,?,?,?,?);";
				PreparedStatement stmt = conn.prepareStatement(insertintotbl);

				stmt.setString(1, questiondto.getQuestion());
				stmt.setString(2, questiondto.getOption1());
				stmt.setString(3, questiondto.getOption2());
				stmt.setString(4, questiondto.getOption3());
				stmt.setString(5, questiondto.getOption4());
				stmt.setString(6, questiondto.getCorrect());
				stmt.setString(7, questiondto.getCategory());
				stmt.setString(8, questiondto.getDifficulty());
				stmt.executeUpdate();
				
			}
			conn.close();
			response="Question List Submitted Successfully";
		} catch (Exception e) {
			response=e.getLocalizedMessage();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}
	
	@GET
	@Path("/getAptiQuesDemo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<QuestionsDto> getDemoApti() {

		ArrayList<QuestionsDto> aptiQuestionsSetDemo = new ArrayList<QuestionsDto>();
		try {
			Connection con = db.getConnection();
			String getrandomquestionsApti = "SELECT * FROM question_tbl where category=? ORDER BY RAND() LIMIT ?";
			PreparedStatement preparedStatement2 = con.prepareStatement(getrandomquestionsApti);
			preparedStatement2.setString(1, "Apti");
			preparedStatement2.setInt(2, 10);
			preparedStatement2.executeQuery();
			ResultSet rs2 = preparedStatement2.executeQuery();
			while (rs2.next()) {
				QuestionsDto questions = new QuestionsDto(rs2.getInt(1), rs2.getString(2), rs2.getString(3),
						rs2.getString(4), rs2.getString(5), rs2.getString(6), rs2.getString(7), rs2.getString(8),
						rs2.getString(9));

				aptiQuestionsSetDemo.add(questions);

			}

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return aptiQuestionsSetDemo;
	}

	@GET
	@Path("/getEngQuesDemo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<QuestionsDto> getDemoEnglish() {
		ArrayList<QuestionsDto> engQuestionsSetDemo = new ArrayList<QuestionsDto>();
		try {
			Connection con = db.getConnection();
			String getrandomquestionsApti = "SELECT * FROM question_tbl where category=? ORDER BY RAND() LIMIT ?";
			PreparedStatement preparedStatement2 = con.prepareStatement(getrandomquestionsApti);
			preparedStatement2.setString(1, "Eng");
			preparedStatement2.setInt(2, 10);
			preparedStatement2.executeQuery();
			ResultSet rs2 = preparedStatement2.executeQuery();
			while (rs2.next()) {
				QuestionsDto questions = new QuestionsDto(rs2.getInt(1), rs2.getString(2), rs2.getString(3),
						rs2.getString(4), rs2.getString(5), rs2.getString(6), rs2.getString(7), rs2.getString(8),
						rs2.getString(9));

				engQuestionsSetDemo.add(questions);

			}

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return engQuestionsSetDemo;
	}

	@GET
	@Path("/getLrQuesDemo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<QuestionsDto> getDemoLr() {
		ArrayList<QuestionsDto> lrQuestionsSetDemo = new ArrayList<QuestionsDto>();
		try {
			Connection con = db.getConnection();
			String getrandomquestionsApti = "SELECT * FROM question_tbl where category=? ORDER BY RAND() LIMIT ?";
			PreparedStatement preparedStatement2 = con.prepareStatement(getrandomquestionsApti);
			preparedStatement2.setString(1, "lr");
			preparedStatement2.setInt(2, 10);
			preparedStatement2.executeQuery();
			ResultSet rs2 = preparedStatement2.executeQuery();
			while (rs2.next()) {
				QuestionsDto questions = new QuestionsDto(rs2.getInt(1), rs2.getString(2), rs2.getString(3),
						rs2.getString(4), rs2.getString(5), rs2.getString(6), rs2.getString(7), rs2.getString(8),
						rs2.getString(9));

				lrQuestionsSetDemo.add(questions);

			}

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return lrQuestionsSetDemo;
	}

}
