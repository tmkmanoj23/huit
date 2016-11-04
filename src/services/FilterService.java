package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DbConnection;
import dto.CandidateGenDto;
import dto.FilterDto;
import dto.StudentExamDetailsDto;
import dto.TestDto;

@Path("/filter")
public class FilterService {
	DbConnection db = new DbConnection();

	@POST
	@Path("/filterstudent")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<StudentExamDetailsDto> filterStudent(FilterDto dto) 
	{
		String batch = null;
		ArrayList<StudentExamDetailsDto> dtos = new ArrayList<StudentExamDetailsDto>();
		int totalquestion=0;
		int totalmarks=0;
		int totalans=0;
		int aptiCorrect=0;
		int engCorrect=0;
		int lrCorrect=0;
		int total_score;
		int noApti=0;
		int noEng=0;
		int noLr=0;
		int totalcorrect=0;
		int count=0;
		try {
			Connection con = db.getConnection();
//			if(dto.getStudenttotal()==0){
			String selectbatch = "SELECT BATCH,NO_OF_APTI,NO_OF_ENG,NO_OF_LR FROM test_tbl where PASS_KEY=?";
			PreparedStatement preparedStatement1 = con.prepareStatement(selectbatch);
			preparedStatement1.setString(1, dto.getPasskey());
			ResultSet rs2 = preparedStatement1.executeQuery();
			while (rs2.next())
			{
				batch=rs2.getString(1);
				totalquestion=rs2.getInt(2)+rs2.getInt(3)+rs2.getInt(4);
				noApti=rs2.getInt(2);
				noEng=rs2.getInt(3);
				noLr=rs2.getInt(4);
			}
			
			if(dto.getStudenttotal()==0){
			System.out.println("batch:"+batch);
			String sql = "SELECT NAME,PHONE_NO, EMAIL_ID ,GENDER,SCORE_APTI,SCORE_ENG,SCORE_LR,BATCH,(SCORE_APTI+SCORE_ENG+SCORE_LR) AS TOTAL  from candidate_tbl where SCORE_APTI>=? and SCORE_ENG>=? and SCORE_LR>=? and batch=? ORDER BY TOTAL DESC";
			PreparedStatement preparedStatement11 = con.prepareStatement(sql);
			preparedStatement11.setInt(1, dto.getCutoff_apti());
			preparedStatement11.setInt(2, dto.getCutoff_eng());
			preparedStatement11.setInt(3, dto.getCutoff_lr());
			preparedStatement11.setString(4, batch);
			ResultSet rs1 = preparedStatement11.executeQuery();
			while (rs1.next())
			{
				aptiCorrect=(noApti*rs1.getInt(5))/100;
				engCorrect=(noEng*rs1.getInt(6))/100;
				lrCorrect=(noLr*rs1.getInt(7))/100;
				totalcorrect=aptiCorrect+engCorrect+lrCorrect;
				StudentExamDetailsDto cndto = new StudentExamDetailsDto();
				cndto.setName(rs1.getString(1));
				cndto.setPhone(rs1.getString(2));
				cndto.setEmail(rs1.getString(3));
				cndto.setGender(rs1.getString(4));
				cndto.setScore_apti(rs1.getInt(5));
				cndto.setScore_eng(rs1.getInt(6));
				cndto.setScore_lr(rs1.getInt(7));
				cndto.setBatch(rs1.getString(8));
//				total_score = (cndto.getScore_apti() + cndto.getScore_eng() + cndto.getScore_lr()) / 3;
				totalmarks=(int)(((float)totalcorrect/(float)(totalquestion))*100);
				cndto.setTotal_score(totalmarks);
				if (totalmarks >= dto.getCutoff_total())
				{
					dtos.add(cndto);
				}

			}
			}
			else{
				System.out.println("batch:"+batch);
				String sql = "SELECT NAME,PHONE_NO, EMAIL_ID ,GENDER,SCORE_APTI,SCORE_ENG,SCORE_LR,BATCH,(SCORE_APTI+SCORE_ENG+SCORE_LR) AS TOTAL  from candidate_tbl where SCORE_APTI>=? and SCORE_ENG>=? and SCORE_LR>=? and batch=? ORDER BY TOTAL DESC";
				PreparedStatement preparedStatement11 = con.prepareStatement(sql);
				preparedStatement11.setInt(1, dto.getCutoff_apti());
				preparedStatement11.setInt(2, dto.getCutoff_eng());
				preparedStatement11.setInt(3, dto.getCutoff_lr());
				preparedStatement11.setString(4, batch);
				ResultSet rs1 = preparedStatement11.executeQuery();
				while (rs1.next())
				{
					aptiCorrect=(noApti*rs1.getInt(5))/100;
					engCorrect=(noEng*rs1.getInt(6))/100;
					lrCorrect=(noLr*rs1.getInt(7))/100;
					totalcorrect=aptiCorrect+engCorrect+lrCorrect;
					StudentExamDetailsDto cndto = new StudentExamDetailsDto();
					cndto.setName(rs1.getString(1));
					cndto.setPhone(rs1.getString(2));
					cndto.setEmail(rs1.getString(3));
					cndto.setGender(rs1.getString(4));
					cndto.setScore_apti(rs1.getInt(5));
					cndto.setScore_eng(rs1.getInt(6));
					cndto.setScore_lr(rs1.getInt(7));
					cndto.setBatch(rs1.getString(8));
//					total_score = (cndto.getScore_apti() + cndto.getScore_eng() + cndto.getScore_lr()) / 3;
					totalmarks=(int)(((float)totalcorrect/(float)(totalquestion))*100);
					cndto.setTotal_score(totalmarks);
					if (totalmarks >= dto.getCutoff_total() && count< dto.getStudenttotal())
					{
						dtos.add(cndto);
						count++;
					}

				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtos;

	}
	@GET
	@Path("/getCollageList")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<TestDto> getCollageList() {
		ArrayList<TestDto> list= new ArrayList<TestDto>();
		
		try {
			Connection con = db.getConnection();
			String sqlmonthcurrent = "SELECT DOT,COLLAGE_NAME,Pass_key from  (select * from test_tbl where month(curdate()) =  month(DOT)) as Result";
			
			PreparedStatement preparedStatement = con.prepareStatement(sqlmonthcurrent);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				TestDto dto = new TestDto();
				dto.setDot(rs.getDate(1));
				dto.setCollege(rs.getString(2));
				dto.setPassKey(rs.getString(3));
				list.add(dto);
				
			
			}
			String sqlmonthnext = "SELECT DOT,COLLAGE_NAME,pass_key from  (select * from test_tbl where  month(curdate())+1 =  month(DOT)) as Result";
			PreparedStatement preparedStatement2 = con.prepareStatement(sqlmonthnext);
			ResultSet rs1 = preparedStatement2.executeQuery();
			while (rs1.next()) {
				TestDto dto = new TestDto();
				dto.setDot(rs1.getDate(1));
				dto.setCollege(rs1.getString(2));
				dto.setPassKey(rs.getString(3));
				list.add(dto);
				

			}
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}


}
