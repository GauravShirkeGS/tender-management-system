package com.tender.administratorDao;

import java.util.List;

import com.tender.exceptions.tenderException;
import com.tender.exceptions.venderException;
import com.tender.models.bids;
import com.tender.models.tender;
import com.tender.models.vender;

public interface administratorDao {
//  static String username = "gaurav@611";
//  static String password = "12345";
  
  public String loginAsAdministrator(String username , String password)throws tenderException;
  
  public List<vender> viewAllVender()throws venderException;
  
  public String createNewTenders(tender ten)throws tenderException;
  
  public List<tender> viewAllTenders() throws tenderException;
  
  public List<bids> allBidsOfTender(int tenderId)throws venderException;
  
  public String assignTenderToVender(int tenderId, int venderId)throws venderException;
}
