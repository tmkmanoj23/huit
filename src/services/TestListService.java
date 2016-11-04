package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DbConnection;
import dto.TestDto;

@Path("/testlist")
public class TestListService {
	
	DbConnection db = new DbConnection();

	@GET
	@Path("/getcompletedtest")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<TestDto> getcompletedtest() {
		
		ArrayList<TestDto> testlist = new ArrayList<TestDto>();
		
		try {
			Connection con = db.getConnection();
			String sql = "SELECT  * from test_tbl where  curdate()  >= DOT or STATUS=1 or Status=2";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				TestDto tesDto = new TestDto(rs.getString(1), rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getDate(9),rs.getString(10),rs.getString(11), rs.getInt(12), rs.getInt(13), rs.getInt(14));
				
				testlist.add(tesDto);
				
//				dto.setCount_current_month(rs.getInt(1));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return testlist;
		
		
	}
	
	@GET
	@Path("/getupcomingtest")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<TestDto> getupcomingtest() {
		
		ArrayList<TestDto> testlist = new ArrayList<TestDto>();
		
		try {
			Connection con = db.getConnection();
			String sql = "SELECT  * from test_tbl where  curdate()  <= DOT and Status=0";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				TestDto tesDto = new TestDto(rs.getString(1), rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getDate(9),rs.getString(10),rs.getString(11), rs.getInt(12), rs.getInt(13), rs.getInt(14));
				
				testlist.add(tesDto);
				
//				dto.setCount_current_month(rs.getInt(1));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return testlist;
		
		
	}
}
