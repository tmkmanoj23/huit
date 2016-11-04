package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DbConnection;
import dto.CandidateEduDto;
import dto.CandidateGenDto;


public class ViewCandidateInfoService {
	DbConnection db= new DbConnection();
	

	@GET
	@Path("/viewcondidateGenInfo/{ph}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CandidateGenDto viewCondidateGenInfo(@PathParam("ph")String ph){
		CandidateGenDto dto = new CandidateGenDto();
		try {
			Connection con = db.getConnection();
			System.out.println("Phone:"+ph);
			String stmt = "Select * from candidate_tbl where phone_no=?";
			PreparedStatement preparedStatement = con.prepareStatement(stmt);
			preparedStatement.setString(1, ph);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				
				 dto.setPhone(rs.getString(1));
				 dto.setName(rs.getString(2));
				 dto.setEmail(rs.getString(3));
				 dto.setAddress(rs.getString(5));
				 dto.setGender(rs.getString(6));
				 
			}
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
	@GET
	@Path("/viewcondidateEduInfo/{ph}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CandidateEduDto viewCondidateeduInfo(@PathParam("ph")String ph){
		CandidateEduDto dto = new CandidateEduDto();
		try {
			Connection con = db.getConnection();
			System.out.println("Phone:"+ph);
			String stmt = "Select * from can_edu_tbl where phone_no=?";
			PreparedStatement preparedStatement = con.prepareStatement(stmt);
			preparedStatement.setString(1, ph);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				
				 dto.setBoardTenth(rs.getString(2));
				 dto.setPercentageTenth(rs.getInt(3));
				 dto.setYearTenth(rs.getInt(4));
				 dto.setBoardTwelve(rs.getString(5));
				 dto.setPercentageTwelve(rs.getInt(6));
				 dto.setYearTwelve(rs.getInt(7));
				 dto.setCollegeUG(rs.getString(8));
				 dto.setPercentageUG(rs.getInt(10));
				 dto.setYearUG(rs.getInt(11));
				 dto.setSpecializationUG(rs.getString(9));
				 
			}
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
			 System.out.println(dto.toString());
			return dto;
		
	
	
	
	}	



} 
				
