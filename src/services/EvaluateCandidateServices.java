package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DbConnection;
import dto.CandidateGenDto;

@Path("evaluatetest")
public class EvaluateCandidateServices {

	DbConnection db = new DbConnection();

	@GET
	@Path("evaluateBatch/{passkey}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<CandidateGenDto> evaluateCandidateBatch(@PathParam("passkey") String passkey) {

		ArrayList<CandidateGenDto> studentList = new ArrayList<CandidateGenDto>();
		String batch = "default";
		try {

			Connection con = db.getConnection();

			String batchCollegeList = "select batch from test_tbl where PASS_KEY=?";
			PreparedStatement preparedStatement1 = con.prepareStatement(batchCollegeList);
			preparedStatement1.setString(1, passkey);
			ResultSet rs = preparedStatement1.executeQuery();
			while (rs.next()) {

				batch = rs.getString(1);
			}

			String stmt1 = "Select * from candidate_tbl where batch=?";
			PreparedStatement preparedStatement2 = con.prepareStatement(stmt1);
			preparedStatement2.setString(1, batch);
			ResultSet rs1 = preparedStatement2.executeQuery();
			while (rs1.next()) {
				CandidateGenDto candidateGenDto = new CandidateGenDto(rs1.getString(1), rs1.getString(2),
						rs1.getString(3), rs1.getString(4), rs1.getString(5), rs1.getString(6),
						(Date) rs1.getDate(7), rs1.getInt(8), rs1.getInt(9), rs1.getInt(10), rs1.getInt(11),
						rs1.getString(12), rs1.getString(13));
				studentList.add(candidateGenDto);

			}

			con.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			CandidateGenDto failure=new CandidateGenDto();
			failure.setStatus("Exception caught");
			studentList.add(failure);
			return studentList;
		}
		return studentList;

	}
}