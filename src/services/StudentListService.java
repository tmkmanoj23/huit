package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DbConnection;
import dto.CandidateGenDto;
import dto.StudentExamDetailsDto;

@Path("list")
public class StudentListService {
DbConnection db= new DbConnection();
	

	@GET
	@Path("/studentlist/{passkey}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
   public ArrayList<StudentExamDetailsDto> getStudentList(@PathParam("passkey") String passkey) 
		{
			String batch = "default";
			int totalquestion=0;
			int totalmarks=0;
			int totalans=0;
			int aptiCorrect=0;
			int engCorrect=0;
			int lrCorrect=0;
			int noApti=0;
			int noEng=0;
			int noLr=0;
			int totalcorrect=0;
			ArrayList<StudentExamDetailsDto> dtos = new ArrayList<StudentExamDetailsDto>();
			
			
			try {
				Connection con = db.getConnection();
				String selectbatch = "SELECT BATCH,NO_OF_APTI,NO_OF_ENG,NO_OF_LR FROM test_tbl where PASS_KEY=?";
				PreparedStatement preparedStatement = con.prepareStatement(selectbatch);
				preparedStatement.setString(1, passkey);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next())
				{
					batch = rs.getString(1);
					totalquestion=rs.getInt(2)+rs.getInt(3)+rs.getInt(4);
					noApti=rs.getInt(2);
					noEng=rs.getInt(3);
					noLr=rs.getInt(4);
				}
				String sql = "SELECT NAME,PHONE_NO, EMAIL_ID ,GENDER,SCORE_APTI,SCORE_ENG,SCORE_LR,BATCH,(SCORE_APTI+SCORE_ENG+SCORE_LR) AS TOTAL  from candidate_tbl where batch=? ORDER BY TOTAL DESC";
				PreparedStatement preparedStatement11 = con.prepareStatement(sql);
				
				preparedStatement11.setString(1, batch);
				ResultSet rs1 = preparedStatement11.executeQuery();
				while (rs1.next())
				{
					aptiCorrect=(noApti*rs1.getInt(5))/100;
					engCorrect=(noEng*rs1.getInt(6))/100;
					lrCorrect=(noLr*rs1.getInt(7))/100;
					
					totalcorrect=aptiCorrect+engCorrect+lrCorrect;
					System.out.println(rs1.getString(1));
					StudentExamDetailsDto cndto = new StudentExamDetailsDto();
                      	cndto.setName(rs1.getString(1));
						cndto.setPhone(rs1.getString(2));
						cndto.setEmail(rs1.getString(3));
						cndto.setGender(rs1.getString(4));
						totalans=rs1.getInt(5)+rs1.getInt(6)+rs1.getInt(7);
						cndto.setScore_apti(rs1.getInt(5));
						cndto.setScore_eng(rs1.getInt(6));
						cndto.setScore_lr(rs1.getInt(7));
						cndto.setBatch(rs1.getString(8));
						
						totalmarks=(int)(((float)totalcorrect/(float)(totalquestion))*100);
						System.out.println(totalmarks);
						cndto.setTotal_score(totalmarks);
						dtos.add(cndto);
				}
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
			 System.out.println(dtos.toString());
			return dtos;
		}
	
	@GET
	@Path("/getshortlisted/{passkey}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
   public ArrayList<StudentExamDetailsDto> getShortlisted(@PathParam("passkey") String passkey) 
		{
			String batch = "default";
			int totalquestion=0;
			int totalmarks=0;
			int totalans=0;
			int aptiCorrect=0;
			int engCorrect=0;
			int lrCorrect=0;
			int noApti=0;
			int noEng=0;
			int noLr=0;
			int totalcorrect=0;
			ArrayList<StudentExamDetailsDto> dtos = new ArrayList<StudentExamDetailsDto>();
			
			
			try {
				Connection con = db.getConnection();
				String selectbatch = "SELECT BATCH,NO_OF_APTI,NO_OF_ENG,NO_OF_LR FROM test_tbl where PASS_KEY=?";
				PreparedStatement preparedStatement = con.prepareStatement(selectbatch);
				preparedStatement.setString(1, passkey);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next())
				{
					batch = rs.getString(1);
					totalquestion=rs.getInt(2)+rs.getInt(3)+rs.getInt(4);
					noApti=rs.getInt(2);
					noEng=rs.getInt(3);
					noLr=rs.getInt(4);
				}
				String sql = "SELECT NAME,PHONE_NO, EMAIL_ID ,GENDER,SCORE_APTI,SCORE_ENG,SCORE_LR,BATCH,(SCORE_APTI+SCORE_ENG+SCORE_LR) AS TOTAL  from candidate_tbl where Status='1' and batch=? ORDER BY TOTAL DESC";
				PreparedStatement preparedStatement11 = con.prepareStatement(sql);
				
				preparedStatement11.setString(1, batch);
				ResultSet rs1 = preparedStatement11.executeQuery();
				while (rs1.next())
				{
					aptiCorrect=(noApti*rs1.getInt(5))/100;
					engCorrect=(noEng*rs1.getInt(6))/100;
					lrCorrect=(noLr*rs1.getInt(7))/100;
					
					totalcorrect=aptiCorrect+engCorrect+lrCorrect;
					System.out.println(rs1.getString(1));
					StudentExamDetailsDto cndto = new StudentExamDetailsDto();
                      	cndto.setName(rs1.getString(1));
						cndto.setPhone(rs1.getString(2));
						cndto.setEmail(rs1.getString(3));
						cndto.setGender(rs1.getString(4));
						totalans=rs1.getInt(5)+rs1.getInt(6)+rs1.getInt(7);
						cndto.setScore_apti(rs1.getInt(5));
						cndto.setScore_eng(rs1.getInt(6));
						cndto.setScore_lr(rs1.getInt(7));
						cndto.setBatch(rs1.getString(8));
						
						totalmarks=(int)(((float)totalcorrect/(float)(totalquestion))*100);
						System.out.println(totalmarks);
						cndto.setTotal_score(totalmarks);
						dtos.add(cndto);
				}
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
			 System.out.println(dtos.toString());
			return dtos;
		}
	
}