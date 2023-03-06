package com.tender.administratorDao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.security.auth.login.AccountNotFoundException;

import com.tender.DBUtil.DBUtil;
import com.tender.exceptions.AdministratorException;
import com.tender.exceptions.tenderException;
import com.tender.exceptions.venderException;
import com.tender.models.bids;
import com.tender.models.tender;
import com.tender.models.vender;

public class administratorDaoImpl implements administratorDao{
	
//	======================= Hashing algorithem for password =================
	
	public static String hashingAlgorithem(String password) {
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA");
			messageDigest.update(password.getBytes());
			byte[] byteArray = messageDigest.digest();
			StringBuilder sb = new StringBuilder();
			for(byte b:byteArray) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
		
	}
	
	
//	=================== email validation method ========
			
			public static String emailValidation(String email) {
				if(email == null || email.isEmpty()) {
					return "Invalid";
				}
				
				String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
				"[a-zA-Z0-9_+&*-]+)*@"+
						"(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
				
				Pattern pattern = Pattern.compile(emailRegex);
				if(pattern.matcher(email).matches()) {
					return "Valid";		
					}else {
						return "Invalid";
					}
				                    
			}

	@Override
	public String loginAsAdministrator(String username, String password) throws AdministratorException {
		
		if(hashingAlgorithem(password)!= hashingAlgorithem("12345")) {
			throw new AdministratorException("Password does not match please try again...");
		}
		
		if(emailValidation(username)=="Invalid") {
			throw new AdministratorException("username does not match please try again...");
		}
		
		String msg = "";
		if(username.equals("gaurav@611")) { 
			if( password.equals("12345")) {
			msg = "Welcome Administrator...";
			}
		}else
			throw new AdministratorException("Wrong credentials please try again...");
		
		
		return msg;
	}
	
	

	@Override
	public List<vender> viewAllVender() throws venderException {
		List<vender> list = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("select * from vender");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				vender ven = new vender();
				ven.setVid(rs.getInt("vid"));
				ven.setvName(rs.getString("vname"));
				ven.setEmail(rs.getString("email"));
				ven.setPassword(rs.getString("password"));
				ven.setCompany(rs.getString("company"));
				
				list.add(ven);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new venderException(e.getMessage());
		}
		
		
		
		return list;
	}

	@Override
	public String createNewTenders(tender ten) throws tenderException {
		String msg ="";
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("insert into tender(tid,property,location,startDate,endDate,basePrice) values (?,?,?,?,?,?)");
			ps.setInt(1,ten.getTid());
			ps.setString(2,ten.getProperty());
			ps.setString(3, ten.getLocation());
			ps.setString(4,ten.getStratDate());
			ps.setString(5,ten.getEndDate());
			ps.setInt(6,ten.getBasePrice());
			
			int x = ps.executeUpdate();
			if(x>0) {
				msg = "New Tender Created Sucessfully...";
			PreparedStatement ps1 = conn.prepareStatement("insert into bids(tid,offer) values (?,?)");
			ps1.setInt(1,ten.getTid() );
			ps1.setInt(2, 0);
			ps1.executeUpdate();
		}
			else
				throw new tenderException("Something went wrong. Please try again...");
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new tenderException(e.getMessage());
		}
		
		
		return msg;
	}

	@Override
	public List<tender> viewAllTenders() throws tenderException {
        List<tender> list = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("select * from tender");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				tender ten = new tender();
				ten.setTid(rs.getInt("tid"));
				ten.setProperty(rs.getString("property"));
				ten.setLocation(rs.getString("location"));
				ten.setStratDate(rs.getString("startDate"));
				ten.setEndDate(rs.getString("endDate"));
				ten.setBasePrice(rs.getInt("basePrice"));
				
				list.add(ten);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new tenderException(e.getMessage());
		}
		
		
		
		return list;
	}

	@Override
	public List<bids> allBidsOfTender(int tenderId) throws venderException {
		   List<bids> list = new ArrayList<>();
			
			try(Connection conn = DBUtil.provideConnection()) {
				PreparedStatement ps = conn.prepareStatement("select * from bids b INNER JOIN vender v ON b.vid = v.vid AND tid = ?");
				ps.setInt(1, tenderId);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					bids bid = new bids();
					bid.setVid(rs.getInt("vid"));
					bid.setTid(rs.getInt("tid"));
					bid.setvName(rs.getString("vname"));
					bid.setCompany(rs.getString("company"));
					bid.setOffer(rs.getInt("offer"));
					
					list.add(bid);
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new venderException(e.getMessage());
			}
			
			
			
			return list;
	}

	@Override
	public String assignTenderToVender(int tenderId, int venderId) throws venderException {
		String msg = "";
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement(" update bids set bidStatus = 'Accepted' where vid = ? AND tid = ? ");
			ps.setInt(1, venderId);
			ps.setInt(2, tenderId);
			int x = ps.executeUpdate();
			if(x>0){
				PreparedStatement ps2 = conn.prepareStatement(" update bids set bidStatus = 'Rejected' where vid != ? AND tid = ? ");
				ps2.setInt(1, venderId);
				ps2.setInt(2, tenderId);
				ps2.executeUpdate();
				
				
				msg = "Tender is Assigned sucessfully to vender id: "+venderId;
			}
			else
				throw new venderException("there is no vender with this ID...");
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new venderException(e.getMessage());
		}
		
		
		
		return msg;
	}
	
	
	

}
