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
import dto.TestCountDto;

@Path("/testcount")
public class TestCountService {
	DbConnection db = new DbConnection();

	@GET
	@Path("/getcount")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public TestCountDto gettestcount() {
		TestCountDto dto = new TestCountDto();
		try {
			Connection con = db.getConnection();
			String sqlmonthcurrent = "SELECT  count(PASS_KEY) from  (select * from test_tbl where  month(curdate()) =  month(DOT)) as Result";

			PreparedStatement preparedStatement = con.prepareStatement(sqlmonthcurrent);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				dto.setCount_current_month(rs.getInt(1));
			}
			String sqlmonthnext = "SELECT  count(PASS_KEY) from  (select * from test_tbl where  month(curdate())+1 =  month(DOT)) as Result";
			PreparedStatement preparedStatement2 = con.prepareStatement(sqlmonthnext);
			ResultSet rs1 = preparedStatement2.executeQuery();
			while (rs1.next()) {
				dto.setCount_next_month(rs1.getInt(1));

			}
			String sqltoday = "SELECT  count(PASS_KEY) from  (select * from test_tbl where  day(curdate()) =  day(DOT)) as Result";
			PreparedStatement preparedStatement3 = con.prepareStatement(sqltoday);
			ResultSet rs2 = preparedStatement3.executeQuery();
			while (rs2.next()) {
				dto.setCount_today(rs2.getInt(1));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dto;

	}

}
