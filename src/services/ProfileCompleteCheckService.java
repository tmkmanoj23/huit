package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import dao.DbConnection;
import dto.ProfileDto;

@Path("/check")
public class ProfileCompleteCheckService {
	DbConnection db = new DbConnection();

	@GET
	@Path("/isprofilecmplt/{ph}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String checkprofilecmplte(@PathParam("ph") String ph) {
		ProfileDto dto = new ProfileDto();
String status="defalut";
		try {
			Connection con = db.getConnection();

			String checkifpresent = "SELECT * FROM can_edu_tbl e , candidate_tbl c where c.PHONE_NO=e.PHONE_NO and c.PHONE_NO=?";
			PreparedStatement preparedStatement = con.prepareStatement(checkifpresent);
			preparedStatement.setString(1, ph);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				

				dto.setPhone(rs.getString(1));
				dto.setBoardTenth(rs.getString(2));
				dto.setPercentageTenth(rs.getInt(3));
				dto.setYearTenth(rs.getInt(4));
				dto.setBoardTwelve(rs.getString(5));
				dto.setPercentageTwelve(rs.getInt(6));
				dto.setYearTwelve(rs.getInt(7));
				dto.setCollegeUG(rs.getString(8));
				dto.setSpecializationUG(rs.getString(9));
				dto.setPercentageUG(rs.getInt(10));
				dto.setYearUG(rs.getInt(11));
				dto.setCourseUG(rs.getString(12));
				
				//dto.setPhone(rs.getString(13));
				
				
				dto.setName(rs.getString(14));
				dto.setEmail(rs.getString(15));
//				dto.setPassword(rs.getString(16));
				
				dto.setAddress(rs.getString(17));
				dto.setGender(rs.getString(18));
				dto.setDob((java.util.Date) rs.getDate(19));
				
				System.out.println(dto.getDob());
				System.out.println("h"+rs.getString(14)+"h");
				if(rs.getString(14).length()==0){
					System.out.println("name null");
				}
				System.out.println(rs.getDate(19).toString().length());
				if (rs.getString(17) != null && rs.getString(2) != null && rs.getString(5)	 != null
						&& rs.getString(8) != null && rs.getString(12) != null 
						&& rs.getString(18) != null && rs.getString(14) != null  && dto.getName() != "" && rs.getInt(3) != 0
						&& rs.getInt(6) != 0
						&& rs.getInt(10)!= 0 && rs.getString(1) != null && rs.getString(9) != null
							&&	rs.getInt(4) != 0 && rs.getInt(7) != 0 && rs.getInt(11) != 0 && (java.util.Date) rs.getDate(19)!=null) 
				{
				status=	"eligibleForLiveTest";
				System.out.println(status);
				}else{
				status=	"NotEligibleForLiveTest";
				System.out.println(status);
				}
			}
			System.out.println(dto.toString());
//			if (dto.getAddress() != null && dto.getBoardTenth() != null && dto.getBoardTwelve() != null
//					&& dto.getCollegeUG() != null && dto.getCourseUG() != null 
//					&& dto.getGender() != null && dto.getName() != null  && dto.getName() != "" && dto.getPercentageTenth() != 0
//					&& dto.getPercentageTwelve() != 0 && dto.getPercentageTwelve() != 0
//					&& dto.getPercentageUG() != 0 && dto.getPhone() != null && dto.getSpecializationUG() != null
//					&& dto.getYearTenth() != 0 && dto.getYearTwelve() != 0 && dto.getYearUG() != 0 && dto.getDob()!=null) 
//			{
//			status=	"eligibleForLiveTest";
//			System.out.println(status);
//			}else{
//			status=	"NotEligibleForLiveTest";
//			System.out.println(status);
//			}


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
		return status;
		

	}
}