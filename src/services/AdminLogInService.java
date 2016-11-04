package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DbConnection;
import dto.AdminDto;

@Path("/homeadmin")
public class AdminLogInService {
	

	DbConnection db = new DbConnection();
	@POST
	@Path("/loginadmin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String candidateLogin(AdminDto dto) {
		String ph = "Not Found";
		try {
			Connection con = db.getConnection();

			String checkifpresent = "Select phone from admin_tbl where email=? and password=?";
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
}
