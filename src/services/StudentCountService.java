package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DbConnection;
import dto.StudentCoutDto;

@Path("/count")
public class StudentCountService {

	@GET
	@Path("/getstudentcount")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public StudentCoutDto gettestcount() {
		DbConnection db= new DbConnection();
		StudentCoutDto dto = new StudentCoutDto();
		try {
			Connection con = db.getConnection();
			String sqlmonthcurrent = "SELECT  count(PHONE_NO) from (select * from candidate_tbl where STATUS =1) as Result";
            PreparedStatement preparedStatement = con.prepareStatement(sqlmonthcurrent);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				dto.setSelected_student(rs.getInt(1));
			}
			String sqlmonthnext = "SELECT  count(PHONE_NO) from  (select * from candidate_tbl where STATUS =2)as Result;";
			PreparedStatement preparedStatement2 = con.prepareStatement(sqlmonthnext);
			ResultSet rs1 = preparedStatement2.executeQuery();
			while (rs1.next()) {
				dto.setUnselected_student(rs1.getInt(1));

			}
		
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dto;

	}

}

