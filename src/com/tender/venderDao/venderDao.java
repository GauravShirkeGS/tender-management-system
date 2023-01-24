package com.tender.venderDao;

import java.util.List;

import com.tender.exceptions.tenderException;
import com.tender.exceptions.venderException;
import com.tender.models.bids;
import com.tender.models.tender;
import com.tender.models.vender;

public interface venderDao {

	public String registerNewVender(vender ven)throws venderException;
	public vender loginAsVender(String username, String Password)throws venderException;
	public List<tender> viewAllCurrentTenders() throws tenderException;
	public String placeBidAgainsTender(int tenderId,int offer) throws tenderException;
	public String viewStatusofBid(int tenderId)throws tenderException;
	public List<bids> viewOwnBidHistory() throws venderException ;
}
