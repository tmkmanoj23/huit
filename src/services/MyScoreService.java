package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DbConnection;
import dto.CandidateGenDto;
import dto.MyScoreDto;
import dto.SubmitDto;
import dto.TestDto;

@Path("/myscore")
public class MyScoreService {
	DbConnection db1 = new DbConnection();
	CandidateGenDto candidateGenDto = new CandidateGenDto();
	TestDto testDto = new TestDto();
	SubmitDto submitDto = new SubmitDto();

	@GET
	@Path("/getscore/{ph}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MyScoreDto getMyscore(@javax.ws.rs.PathParam("ph") String ph) {

		float Mrks = 0;
		float total = 0;
		MyScoreDto dto = new MyScoreDto();

		try {

			Connection con = db1.getConnection();
			String sql = "select NO_OF_APTI,NO_OF_ENG,NO_OF_LR,SCORE_APTI,SCORE_ENG,SCORE_LR from test_tbl t, candidate_tbl c where c.PHONE_NO=? and c.BATCH=t.BATCH ";
			PreparedStatement preparedStatement1 = con.prepareStatement(sql);
			preparedStatement1.setString(1, ph);
			preparedStatement1.executeQuery();

			ResultSet rs = preparedStatement1.executeQuery();
			while (rs.next()) {
				int score_apti = rs.getInt(4);
				int score_eng = rs.getInt(5);
				int score_lr = rs.getInt(6);
				Mrks = score_apti + score_eng + score_lr;

				int no_apti = rs.getInt(1);
				int no_eng = rs.getInt(2);
				int no_lr = rs.getInt(3);
				total = no_apti + no_eng + no_lr;

				int percentage = (int) ((Mrks / total) * 100);

				MyScoreDto myscoreDto1 = new MyScoreDto(rs.getInt(1),
						rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
						rs.getInt(6), percentage);
				dto = myscoreDto1;
			}

			rs.close();
			con.close();

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
		return dto;

	}
}