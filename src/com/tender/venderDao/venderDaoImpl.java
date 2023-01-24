package com.tender.venderDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import com.tender.DBUtil.DBUtil;
import com.tender.exceptions.tenderException;
import com.tender.exceptions.venderException;
import com.tender.models.bids;
import com.tender.models.tender;
import com.tender.models.vender;

public class venderDaoImpl implements venderDao {
	
	private static int existingvenderId;

	@Override
	public String registerNewVender(vender ven) throws venderException {
		String msg = "";
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("insert into vender(vname,email,password,company)values (?,?,?,?)");
			ps.setString(1,ven.getvName());
			ps.setString(2,ven.getEmail());
			ps.setString(3, ven.getPassword());
			ps.setString(4,ven.getCompany());
			
			int x =ps.executeUpdate();
			if(x>0) 
				msg = "Your Registration is sucessful...";
			else
				throw new venderException("Wrong credentials. Please try again...");
			
			
			
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new venderException(e.getMessage());
		}
		
		
		return msg;
	}

	@Override
	public vender loginAsVender(String username, String Password) throws venderException {
		vender ven = null;
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("select * from vender where email =? AND password = ?");
			ps.setString(1,username);
			ps.setString(2, Password);
			
			
			ResultSet rs =ps.executeQuery();
			if(rs.next()) { 
				ven = new vender();
				ven.setVid(rs.getInt("vid"));
				ven.setvName(rs.getString("vname"));
				ven.setEmail(rs.getString("email"));
				ven.setPassword(rs.getString("password"));
				ven.setCompany(rs.getString("company"));
				
				existingvenderId = ven.getVid();
				
			}else
				throw new venderException("Wrong credentials. Please try again...");
			
			
			
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new venderException(e.getMessage());
		}
		
		
		return ven;
	}

	@Override
	public List<tender> viewAllCurrentTenders() throws tenderException {
		List<tender> list = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("select * from tender where tid = (select tid from bids where bidStatus IS NULL)");
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
	public String placeBidAgainsTender(int tenderId, int offer) throws tenderException {
		String msg = "";
		
		try (Connection conn = DBUtil.provideConnection()){
			PreparedStatement ps = conn.prepareStatement("insert into bids(tid,vid,offer) values(?,?,?)");
			ps.setInt(1, tenderId);
			ps.setInt(2, existingvenderId);
			ps.setInt(3, offer);
			int x = ps.executeUpdate();
			if(x>0) {
				msg = "bid is sucessfull on tender id: "+tenderId+" of offer: "+offer;
				PreparedStatement ps1 = conn.prepareStatement("delete from bids where tid= ? AND vid IS NULL");
				ps1.setInt(1, tenderId);
				ps1.executeUpdate();
			}else
				throw new tenderException("tender id is wrong...");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new tenderException(e.getMessage());
		}
		
		
		
		return msg;
	}

	@Override
	public String viewStatusofBid(int tenderId) throws tenderException {
              String msg = "";
		
		try (Connection conn = DBUtil.provideConnection()){
			PreparedStatement ps = conn.prepareStatement("select * from bids where vid= ? AND tid= ?");
			ps.setInt(2, tenderId);
			ps.setInt(1, existingvenderId);
		
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				msg = rs.getString("bidStatus");
			}else
				throw new tenderException("tender id is wrong...");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new tenderException(e.getMessage());
		}
		
		
		
		
		return msg;
	}

	@Override
	public List<bids> viewOwnBidHistory() throws venderException {
		 List<bids> list = new ArrayList<>();
			
			try(Connection conn = DBUtil.provideConnection()) {
				PreparedStatement ps = conn.prepareStatement("select * from bids b INNER JOIN vender v ON b.vid = v.vid AND v.vid = ?");
				ps.setInt(1, existingvenderId);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					bids bid = new bids();
					bid.setVid(rs.getInt("vid"));
					bid.setTid(rs.getInt("tid"));
					bid.setvName(rs.getString("vname"));
					bid.setCompany(rs.getString("company"));
					bid.setOffer(rs.getInt("offer"));
					bid.setBidStatus(rs.getString("bidStatus"));
					list.add(bid);
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new venderException(e.getMessage());
			}
			
			
			
			return list;
	}

}
