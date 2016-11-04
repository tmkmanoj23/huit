package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import dao.DbConnection;

@Path("/statusget")
public class CandidateStatus {
	
	DbConnection db = new DbConnection();
	
	@GET
	@Path("/getStatus/{ph}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getStatus( @PathParam("ph") String ph){
		String Status = "default";
		
		try {
			Connection con = db.getConnection();

			String statusCheck = "Select * from candidate_tbl where phone_no=?";
			PreparedStatement preparedStatement = con.prepareStatement(statusCheck);
			preparedStatement.setString(1, ph);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Status = rs.getString(10);
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
				
			return Status;
		
	}
}
