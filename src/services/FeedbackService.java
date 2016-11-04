package services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import dao.DbConnection;
import dto.FeedbackDto;


@Path("/feedback")
public class FeedbackService {
DbConnection db1 = new DbConnection();

	@POST
	@Path("/createFeedback")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createfeedback(FeedbackDto fdbckdto) {
	
		try {
			Connection conn = db1.getConnection();
			String insertintotbl = "INSERT INTO feedback_tbl(phone_no,feedback) VALUES (?,?)";
			PreparedStatement stmt = conn.prepareStatement(insertintotbl);
			
			stmt.setString(1, fdbckdto.getPhone());
			stmt.setString(2, fdbckdto.getFeedback());
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

		return "feedback updated";
	}

	@GET
	@Path("/getfeedback")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<FeedbackDto> getfeedback() {

		ArrayList<FeedbackDto> allfeedback = new ArrayList<FeedbackDto>();
		try {
			Connection conn = db1.getConnection();
			String sql = "SELECT * FROM feedback_tbl";

			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {

				FeedbackDto tesDto = new FeedbackDto(rs.getString(1), rs.getString(2));

			    allfeedback.add(tesDto);
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
		return allfeedback;

	}

	@POST
	@Path("/updatefeedback")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateFeedback(FeedbackDto fdbckDto) {

		try {
			Connection conn = db1.getConnection();
			String updateintotsttbl = "UPDATE feedback_tbl SET feedback=? WHERE phone_no=?";
			PreparedStatement stmt = conn.prepareStatement(updateintotsttbl);
			stmt.setString(1, fdbckDto.getFeedback());
			stmt.setString(2, fdbckDto.getPhone());
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
	@Path("/deletefeeback")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String deletefeedback(FeedbackDto fdbkdto) {

		try {
			Connection conn = db1.getConnection();
			Statement statement = conn.createStatement();
			String deletfromtsttbl = "DELETE FROM feedback_tbl WHERE phone_no=?";
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

	@POST
	@Path("/getfeedbackonphone_no")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<FeedbackDto> getRcordPassKey(FeedbackDto dto1) {
		ArrayList<FeedbackDto> details = new ArrayList<FeedbackDto>();

		try {
			Connection conn = db1.getConnection();
			String sql = "SELECT * FROM feedback_tbl WHERE PHONE_NO=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, dto1.getPhone());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				FeedbackDto fdbckdto = new FeedbackDto(rs.getString(1),rs.getString(2));

				details.add(fdbckdto);
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
		return details;

	}
	}
