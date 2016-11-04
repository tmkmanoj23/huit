package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
//import java.text.SimpleDateFormat;
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
import dto.QuestionPaperDto;
import dto.TestDto;

@Path("/test")
public class TestService {
	DbConnection db1 = new DbConnection();

	@POST
	@Path("/createTest")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createTest(TestDto dto) {
		String passkey = "default";
		try {
			Connection conn = db1.getConnection();

			String firstLetters = "";
			for (String s : dto.getCollege().split(" ")) {
				firstLetters += s.charAt(0);
			}
			Random r = new Random();
			int rno = r.nextInt(999);
			firstLetters += rno;
			passkey = firstLetters;

			String insertintotbl = "INSERT INTO test_tbl (pass_Key, no_Of_Apti,no_Of_Eng,no_Of_LR,Time,batch,collage_name,cut_Off,dot,difficulty, status,time_apti,time_eng,time_lr) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(insertintotbl);
			stmt.setInt(2, dto.getNoOfApti());
			stmt.setInt(3, dto.getNoOfEng());
			stmt.setInt(4, dto.getNoOfLR());
			stmt.setInt(5, dto.getTestTime());
			stmt.setString(6, dto.getBatch());
			stmt.setString(7, dto.getCollege());
			stmt.setInt(8, dto.getCutOff());
			

			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			// Date dtt = myFormat.parse(dto.getDot());
			// java.sql.Date ds = new java.sql.Date(dtt.getTime());
			String date = myFormat.format(dto.getDot());
			stmt.setDate(9, java.sql.Date.valueOf(date));
			stmt.setString(10, dto.getDifficulty());
			stmt.setString(11, "0");
			stmt.setString(1, passkey);
			stmt.setInt(12, dto.getTime_apti());
			stmt.setInt(13, dto.getTime_eng());
			stmt.setInt(14, dto.getTime_lr());
			stmt.executeUpdate();

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

		return passkey;
	}

	@GET
	@Path("/getAllTest")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<TestDto> getTest() {

		ArrayList<TestDto> alltest = new ArrayList<TestDto>();
		try {
			Connection conn = db1.getConnection();
			String sql = "SELECT * FROM test_tbl";

			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {

				TestDto tesDto = new TestDto(rs.getString(1), rs.getInt(2),
						rs.getInt(3), rs.getInt(4), rs.getInt(5),
						rs.getString(6), rs.getString(7), rs.getInt(8),
						rs.getDate(9), rs.getString(10), rs.getString(11),rs.getInt(12),rs.getInt(13),rs.getInt(14));

				// System.out.println(rs.getString(1)+" "+rs.getInt(2)+" "+rs.getInt(3)+" "+rs.getInt(4)+" "+rs.getInt(5)+" "+rs.getString(6)+" "+rs.getString(7)+" "+rs.getInt(8)+" "+rs.getString(9)+" "+
				// rs.getString(10)+" "+rs.getString(11));

				alltest.add(tesDto);
			}

			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return alltest;

	}

	@POST
	@Path("/updateTest")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateTest(TestDto dto) {

		try {
			Connection conn = db1.getConnection();
			String updateintotsttbl = "UPDATE test_tbl SET no_Of_Apti=?,no_Of_Eng=?,no_Of_LR=?,dot=?,difficulty=? ,time_apti=?,time_eng=?,time_lr=? WHERE pass_key=?";
			PreparedStatement stmt = conn.prepareStatement(updateintotsttbl);
			stmt.setString(9, dto.getPassKey());
			stmt.setInt(1, dto.getNoOfApti());
			stmt.setInt(2, dto.getNoOfEng());
			stmt.setInt(3, dto.getNoOfLR());
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = myFormat.format(dto.getDot());
			stmt.setDate(4, java.sql.Date.valueOf(date));
			stmt.setString(5, dto.getDifficulty());
			stmt.setInt(6, dto.getTime_apti());
			stmt.setInt(7, dto.getTime_eng());
			stmt.setInt(8, dto.getTime_lr());
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

		return "updated";
	}

	@POST
	@Path("/deleteTest")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteTest(TestDto dto) {

		try {
			Connection conn = db1.getConnection();
			Statement statement = conn.createStatement();
			String deletfromtsttbl = "DELETE FROM test_tbl WHERE passkey=?";

			statement.executeUpdate(deletfromtsttbl);

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

		return "Deleted";
	}

	@GET
	@Path("/getRcordOnPassKey/{passkey}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TestDto getRcordPassKey(@PathParam("passkey") String passkey) {
		TestDto dto=new TestDto();
		try {
			Connection conn = db1.getConnection();
			String sql = "SELECT * FROM test_tbl WHERE PASS_KEY=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, passkey);

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				TestDto tesDto = new TestDto(rs.getString(1), rs.getInt(2),
						rs.getInt(3), rs.getInt(4), rs.getInt(5),
						rs.getString(6), rs.getString(7), rs.getInt(8),
						rs.getDate(9), rs.getString(10), rs.getString(11), rs.getInt(12), rs.getInt(13), rs.getInt(14));

				// System.out.println(rs.getString(1)+" "+rs.getInt(2)+" "+rs.getInt(3)+" "+rs.getInt(4)+" "+rs.getInt(5)+" "+rs.getString(6)+" "+rs.getString(7)+" "+rs.getInt(8)+" "+rs.getString(9)+" "+
				// rs.getString(10)+" "+rs.getString(11));
				dto=tesDto;
			}

			rs.close();

			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return dto;

	}

	@GET
	@Path("/testValidation/{passkey}/{ph}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String testValidation(@PathParam("passkey") String passkey,
			@PathParam("ph") String ph) {
		int count = 0;
		String response = "Default";
		int attempt = 0;
		try {
			String batch="default";
			Connection con = db1.getConnection();
			String stmt1 = "Select attempt from candidate_tbl where phone_no=?";
			PreparedStatement preparedStatement1 = con.prepareStatement(stmt1);
			preparedStatement1.setString(1, ph);
			preparedStatement1.executeQuery();
			ResultSet rs1 = preparedStatement1.executeQuery();
			while (rs1.next()) {
				attempt = rs1.getInt(1);
				System.out.println(attempt);
			}
			if (attempt == 0) {
				String getbatch = "select batch from test_tbl where pass_key=?";
				PreparedStatement preparedStatement5 = con.prepareStatement(getbatch);
				preparedStatement5.setString(1, passkey);
				ResultSet rs5 = preparedStatement5.executeQuery();
				while (rs5.next()) {
					batch=rs5.getString(1);
				}
				
				String batchUpdateOfStudent = "UPDATE candidate_tbl SET BATCH=? WHERE PHONE_NO=?";
				PreparedStatement preparedStatement6 = con.prepareStatement(batchUpdateOfStudent);
				preparedStatement6.setString(1, batch);
				preparedStatement6.setString(2, ph);
				preparedStatement6.executeUpdate();
				
				
				String stmt = "select * from candidate_tbl c, test_tbl t where c.PHONE_NO=? and t.PASS_KEY=? and c.BATCH= t.BATCH";
				PreparedStatement preparedStatement = con.prepareStatement(stmt);
				preparedStatement.setString(1, ph);
				preparedStatement.setString(2, passkey);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					count++;
					System.out.println(rs.getString(1));
				}
				System.out.println(count);
				if (count > 0) {
					response = "Success";
					String stmt2 = "UPDATE candidate_tbl SET ATTEMPT='1' WHERE PHONE_NO=?";
					PreparedStatement preparedStatement2 = con.prepareStatement(stmt2);
					preparedStatement2.setString(1, ph);
					preparedStatement2.executeUpdate();
				} else {
					response = "Failure";
				}
			} else {
				response = "Attempt Exceded";
			}

		} catch (SQLException se) {
			// Handle errors for JDBC
			response = "SQLException";

			se.printStackTrace();
			return response;

		} catch (Exception e) {
			// Handle errors for Class.forName
			response = "Exception";

			e.printStackTrace();
			return response;
		}
		return response;
	}
}