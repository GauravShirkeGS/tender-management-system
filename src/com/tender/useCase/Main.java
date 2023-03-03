package com.tender.useCase;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.tender.administratorDao.administratorDao;
import com.tender.administratorDao.administratorDaoImpl;
import com.tender.exceptions.tenderException;
import com.tender.exceptions.venderException;
import com.tender.models.bids;
import com.tender.models.tender;
import com.tender.models.vender;
import com.tender.venderDao.venderDao;
import com.tender.venderDao.venderDaoImpl;

public class Main {

	public static void main(String[] args) {

     Scanner sc = new Scanner(System.in);
     
     System.out.println(ConsoleColors.BLACK_UNDERLINED+ConsoleColors.RED_BOLD_BRIGHT+" ********** WELCOME TO TENDER MANAGEMENT APPLICATION ********** "+ConsoleColors.RESET);
 	
//   =============================  here i give three option to operate application =========================
     
     System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + "+---------------------------+" + "\n"
                                                + "| 1. Login As Administrator |" + "\n"
    		                                    + "| 2. Login as Vender        |" + "\n"
                                                + "| 3. Exit                   |" + "\n"
    		                                    + "+---------------------------+" + ConsoleColors.RESET);
//    selecting variable to operate application ===========================
     int start = sc.nextInt();
     
     
     if(start==1) {
    	 
//    	 ================================= Administator logics and methods ==============================
    	 
    	 System.out.println(ConsoleColors.BLACK_UNDERLINED+ConsoleColors.RED_BOLD_BRIGHT+" ********** WELCOME ADMINISTRATOR ********** "+ConsoleColors.RESET);
     	
//    	========================== addministrator login logic ===============================
    	 
    	 while(true) {
    	 System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"ENTER USERNAME : "+ConsoleColors.RESET);
    	 String username = sc.next();
    	 
    	 System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"ENTER PASSWORD : "+ConsoleColors.RESET);
    	 String password = sc.next();
    	 
    	 administratorDao dao = new administratorDaoImpl();
    	 try {
    		 
			String msg = dao.loginAsAdministrator(username ,password);
			System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT+msg+ConsoleColors.RESET);
		} catch (tenderException e) {
			// TODO Auto-generated catch block
			System.out.println(ConsoleColors.RED_BOLD_BRIGHT+e.getMessage()+ConsoleColors.RESET);
			
			continue;
		}
          break;
    	 
    	 } 
    	 
    	 while(true) {
    		 
//    		 ====================== administrator methods after login ======================
    	 
    	 System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + "+------------------------------- +" + "\n"
                                                                + "| 1. View all vendors            |" + "\n"
                                                                + "| 2. Create new tenders          |" + "\n"
                                                                + "| 3. View all tenders            |" + "\n"
                                                                + "| 4. View all bids of tender     |" + "\n"
                                                                + "| 5. Assign tender to the Vendor |" + "\n"
                                                                + "| 6. Exit                        |" + "\n"
                                                                + "+--------------------------------+" + ConsoleColors.RESET);
    	 
    	  int pointer = sc.nextInt();
    	  administratorDao dao = new administratorDaoImpl();
    	  
//    	  ======================= view all venders method ==========================
    	  
    	  if(pointer ==1) {
    		 try {
    			 System.out.println(ConsoleColors.BLACK_UNDERLINED+ConsoleColors.GREEN_BOLD_BRIGHT+" ****** TOTAL VENDORS REGISTERED TILL NOW. ****** "+ConsoleColors.RESET);
				List<vender> list = dao.viewAllVender();
				
				for (vender ven : list) {
					System.out.println(ConsoleColors.ORANGE+"Vender Id: "+ ven.getVid()+ConsoleColors.RESET);
					System.out.println(ConsoleColors.ORANGE+"Vender name: "+ ven.getvName()+ConsoleColors.RESET);
					System.out.println(ConsoleColors.ORANGE+"Vender email: "+ ven.getEmail()+ConsoleColors.RESET);
					System.out.println(ConsoleColors.ORANGE+"Vender password: "+ ven.getPassword()+ConsoleColors.RESET);
					System.out.println(ConsoleColors.ORANGE+"Vender company: "+ ven.getCompany()+ConsoleColors.RESET);
					System.out.println(ConsoleColors.BLACK_STRIKE+"*************************************1"+ConsoleColors.RESET);
				}
				
			} catch (venderException e) {
				// TODO Auto-generated catch block
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT+e.getMessage()+ConsoleColors.RESET);
			}
    		 
    		 continue;
    	  }
    	  else if (pointer==2) {
    		  
//    		  ============================== creating new tenders logic ======================
    		  
    		  tender td = new tender();
    		  
    		    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"Enter tender Id : "+ConsoleColors.RESET);
 	    	    int tid = sc.nextInt();
    		  
    		     System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"Enter Property : "+ConsoleColors.RESET);
    	    	 String property = sc.next();
    	    	
    	    	 System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"Enter Location : "+ConsoleColors.RESET);
    	    	 String location = sc.next();
    	    	 
    	    	 System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"Enter start date (YYYY/MM/DD)? : "+ConsoleColors.RESET);
    	    	 String startDate= sc.next();
    	    	 
    	    	 System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"Enter end date (YYYY/MM/DD)? : "+ConsoleColors.RESET);
    	    	 String endDate = sc.next();
    	    	 
    	    	 System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"Enter base price : "+ConsoleColors.RESET);
    	    	 int basePrice = sc.nextInt();
    	    	 td.setTid(tid);
    	    	 td.setBasePrice(basePrice);
    	    	 td.setEndDate(endDate);
    	    	 td.setLocation(location);
    	    	 td.setProperty(property);
    	    	 td.setStratDate(startDate);
    	    	 
    		  
    		  try {
				String msg =dao.createNewTenders(td);
				
				System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT+msg+ConsoleColors.RESET);
				
			} catch (tenderException e) {
				// TODO Auto-generated catch block
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT+e.getMessage()+ConsoleColors.RESET);
			}
    		  continue;
    	  }
    	  else if(pointer==3) {
    		  
//    		================================  View all tenders method ================================
                     
    			 try {
        			 System.out.println(ConsoleColors.BLACK_UNDERLINED+ConsoleColors.GREEN_BOLD_BRIGHT+" ****** TOTAL TENDERS LIST. ****** "+ConsoleColors.RESET);
    				List<tender> list = dao.viewAllTenders();
    				
    				for (tender ven : list) {
    					System.out.println(ConsoleColors.ORANGE+"Tender Id: "+ ven.getTid()+ConsoleColors.RESET);
    					System.out.println(ConsoleColors.ORANGE+"Tender Property: "+ ven.getProperty()+ConsoleColors.RESET);
    					System.out.println(ConsoleColors.ORANGE+"Tender Location: "+ ven.getLocation()+ConsoleColors.RESET);
    					System.out.println(ConsoleColors.ORANGE+"Tender Start Date: "+ ven.getStratDate()+ConsoleColors.RESET);
    					System.out.println(ConsoleColors.ORANGE+"Tender End Date: "+ ven.getEndDate()+ConsoleColors.RESET);
    					System.out.println(ConsoleColors.ORANGE+"Tender Base Price: "+ ven.getBasePrice()+ConsoleColors.RESET);
    					System.out.println(ConsoleColors.BLACK_STRIKE+"*************************************1"+ConsoleColors.RESET);
    				}
    				
    			} catch (tenderException e) {
    				// TODO Auto-generated catch block
    				System.out.println(ConsoleColors.RED_BOLD_BRIGHT+e.getMessage()+ConsoleColors.RESET);
    			}
        		 
        		 continue;
    	  }
    	  else if(pointer==4) {
    		  
//    	=========================== View all bids of tender method ============================== 
                     
    		  try {
    			
    			  System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"Enter tender Id : "+ConsoleColors.RESET);
     	    	 int tenderId = sc.nextInt();
    			  
     			 System.out.println(ConsoleColors.BLACK_UNDERLINED+ConsoleColors.GREEN_BOLD_BRIGHT+" ****** TOTAL TENDERS LIST. ****** "+ConsoleColors.RESET);
     			 
 				List<bids> list = dao.allBidsOfTender(tenderId);
 				
 				for (bids ven : list) {
 					System.out.println(ConsoleColors.ORANGE+"Tender Id: "+ ven.getTid()+ConsoleColors.RESET);
 					System.out.println(ConsoleColors.ORANGE+"Vendor Id: "+ ven.getVid()+ConsoleColors.RESET);
 					System.out.println(ConsoleColors.ORANGE+"Vendor Name: "+ ven.getvName()+ConsoleColors.RESET);
 					System.out.println(ConsoleColors.ORANGE+"Bid Company: "+ ven.getCompany()+ConsoleColors.RESET);
 					System.out.println(ConsoleColors.ORANGE+"Bid Offer in Rs: "+ ven.getOffer()+ConsoleColors.RESET);
 					System.out.println(ConsoleColors.BLACK_STRIKE+"*************************************1"+ConsoleColors.RESET);
 				}
 				
 			} catch (venderException e) {
 				// TODO Auto-generated catch block
 				System.out.println(ConsoleColors.RED_BOLD_BRIGHT+e.getMessage()+ConsoleColors.RESET);
 			}
     		 
     		 continue;
 	  }  
    	  else if(pointer==5) {
    		  
//    		  ============================== Assign tender to the Vendor method =============================
    		  
    		  
    		  System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"Enter tender Id : "+ConsoleColors.RESET);
  	    	 int tenderId = sc.nextInt();
  	    	 
  	    	  System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"Enter Vendor Id : "+ConsoleColors.RESET);
  	    	 int venderId = sc.nextInt();
    		  
    		  try {
				String msg = dao.assignTenderToVender(tenderId, venderId);
				
				 System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT+msg+ConsoleColors.RESET);
				
			} catch (venderException e) {
				// TODO Auto-generated catch block
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT+e.getMessage()+ConsoleColors.RESET);
			}
    		  
    		 continue; 
    	  }else {
    		 
    		  System.out.println(ConsoleColors.BLACK_UNDERLINED+ConsoleColors.RED_BOLD_BRIGHT+" ********** THANK YOU FOR USING THIS APPLICATION ********** "+ConsoleColors.RESET);
    	    	
    		  break;
    	     }
    	 }
    	 
     }
     
//     ============================================ Venders methods logic ======================================
     
     else if(start ==2) {
    	  
    	boolean flag = true;
    	   
    	 System.out.println(ConsoleColors.BLACK_UNDERLINED+ConsoleColors.RED_BOLD_BRIGHT+" ********** WELCOME VENDOR ********** "+ConsoleColors.RESET);
    	
    		 while(true) {
  			
    			 
    			 System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + "+---------------------------+" + "\n"
    	                 + "| 1. Register new vendor    |" + "\n"
    	                 + "| 2. Login by email         |" + "\n"
    	                 + "| 3. Exit                   |" + "\n"
    	                 + "+---------------------------+" + ConsoleColors.RESET);
    	    	 
    	    	 int gs = sc.nextInt();
    	    	 venderDao dao = new venderDaoImpl();
    			 
    			 if(gs==1) {
    				 
//    				 ========================== registrating logic of vendore ========================
    						 
    				 System.out.println(ConsoleColors.BLACK_UNDERLINED+ConsoleColors.GREEN_BOLD_BRIGHT+" ****** Register vendor details. ******* "+ConsoleColors.RESET); 
    				 
    				 vender ven = new vender();
    				 System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"Enter Name : "+ConsoleColors.RESET);
    	  	    	 String vName = sc.next();
    				 
    	  	    	 System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"Enter Email : "+ConsoleColors.RESET);
    	  	    	 String email = sc.next();
    				 
    	  	    	 System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"Enter Password : "+ConsoleColors.RESET);
    	  	    	 String password = sc.next();
    				 
    	  	    	 System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"Enter Company name : "+ConsoleColors.RESET);
    	  	    	 String company = sc.next();
    	  	    	 
    	  	    	 ven.setvName(vName);
    	  	    	 ven.setEmail(email);
    	  	    	 ven.setPassword(password);
    	  	    	 ven.setCompany(company);
    	  	    	 
    	  	    	try {
						String msg = dao.registerNewVender(ven);
						
						 System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT+msg+ConsoleColors.RESET);
						
					} catch (venderException e) {
						// TODO Auto-generated catch block
						System.out.println(ConsoleColors.RED_BOLD_BRIGHT+e.getMessage()+ConsoleColors.RESET);
					}
    				 
    			 continue;
    		 }
    			 else if(gs==2) {
    				 
//    				============================= After registration login logic of vendors ====================
    				 
    				 System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"Enter username : "+ConsoleColors.RESET);
    	  	    	 String username = sc.next();
    	  	    	 
    	  	    	 System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"Enter password : "+ConsoleColors.RESET);
    	  	    	 String password = sc.next();
    	  	    	 
    	  	    	 try {
						vender ven = dao.loginAsVender(username, password);
					
						 System.out.println(ConsoleColors.BLACK_UNDERLINED+ConsoleColors.GREEN_BOLD_BRIGHT+" ****** Log in Sucessfull.. ******"+ConsoleColors.RESET);
						 
						 System.out.println(ConsoleColors.ORANGE+"Profile"+ConsoleColors.RESET);
						 System.out.println(ConsoleColors.BLACK_STRIKE+"*************************************"+ConsoleColors.RESET);
						 System.out.println(ConsoleColors.ORANGE+"Vender Id: "+ ven.getVid()+ConsoleColors.RESET);
							System.out.println(ConsoleColors.ORANGE+"Vender name: "+ ven.getvName()+ConsoleColors.RESET);
							System.out.println(ConsoleColors.ORANGE+"Vender email: "+ ven.getEmail()+ConsoleColors.RESET);
							System.out.println(ConsoleColors.ORANGE+"Vender password: "+ ven.getPassword()+ConsoleColors.RESET);
							System.out.println(ConsoleColors.ORANGE+"Vender company: "+ ven.getCompany()+ConsoleColors.RESET);
							System.out.println(ConsoleColors.BLACK_STRIKE+"*************************************"+ConsoleColors.RESET);
							
							
						
					} catch (venderException e) {
						// TODO Auto-generated catch block
						System.out.println(ConsoleColors.RED_BOLD_BRIGHT+e.getMessage()+ConsoleColors.RESET);
						continue;
					}
    	  	    	 
    	  	    	break;
    			 }
    			 else {
//    				 ============== exceptoin for wrong details of vender ======================
//    				 if vendor give wrong detailes then flag will break the loop ==========
    				 flag = false;
    				 System.out.println(ConsoleColors.BLACK_UNDERLINED+ConsoleColors.RED_BOLD_BRIGHT+" ********** THANK YOU FOR USING THIS APPLICATION ********** "+ConsoleColors.RESET);
    				 break;
    			 }
    			 
    	 }
    	 
    	 if(flag) {
    		 
//    		 =================== Vendor logged suceesfully then vender methods ===========================
    		 
    		 while(true) {
    			
    			 System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + "+------------------------------- +" + "\n"
                         + "| 1. View all current tenders    |" + "\n"
                         + "| 2. Place a bid to tender       |" + "\n"
                         + "| 3. View status of bid          |" + "\n"
                         + "| 4. View bid history            |" + "\n"
                         + "| 5. Exit                        |" + "\n"
                         + "+--------------------------------+" + ConsoleColors.RESET);
    			 
    			 int pointer = sc.nextInt();
    			 venderDao dao = new venderDaoImpl();
    			 
    			 if(pointer==1) {
    				 
//    				=========================== View all current tenders method ==========================  
    				 
    				try {
						List<tender> list = dao.viewAllCurrentTenders();
						
						if(list.size()==0) {
							 System.out.println(ConsoleColors.BLACK_UNDERLINED+ConsoleColors.GREEN_BOLD_BRIGHT+" *****NO CURRENT TENDERS AT THIS POINT. ****** "+ConsoleColors.RESET);
						}else {
						 System.out.println(ConsoleColors.BLACK_UNDERLINED+ConsoleColors.GREEN_BOLD_BRIGHT+" ***** CURRENT TENDERS LIST. ****** "+ConsoleColors.RESET);
		    				
		    				for (tender ven : list) {
		    					System.out.println(ConsoleColors.ORANGE+"Tender Id: "+ ven.getTid()+ConsoleColors.RESET);
		    					System.out.println(ConsoleColors.ORANGE+"Tender Property: "+ ven.getProperty()+ConsoleColors.RESET);
		    					System.out.println(ConsoleColors.ORANGE+"Tender Location: "+ ven.getLocation()+ConsoleColors.RESET);
		    					System.out.println(ConsoleColors.ORANGE+"Tender Start Date: "+ ven.getStratDate()+ConsoleColors.RESET);
		    					System.out.println(ConsoleColors.ORANGE+"Tender End Date: "+ ven.getEndDate()+ConsoleColors.RESET);
		    					System.out.println(ConsoleColors.ORANGE+"Tender Base Price: "+ ven.getBasePrice()+ConsoleColors.RESET);
		    					System.out.println(ConsoleColors.BLACK_STRIKE+"*************************************1"+ConsoleColors.RESET);
		    				}
						}
						
					} catch (tenderException e) {
						// TODO Auto-generated catch block
						System.out.println(ConsoleColors.RED_BOLD_BRIGHT+e.getMessage()+ConsoleColors.RESET);
				
					}
    				 
    				continue; 
    			 }
    			 else if(pointer==2) {
    				
//    	  	    	 =================== Place a bid to tender method =============================
    	  	    	
    	  	    	 
    	  	    	 try {
    	  	    		 
        				 System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"Enter tender Id : "+ConsoleColors.RESET);
        	  	    	 int tid = sc.nextInt();
        	  	    	 
        	  	    	 System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"Enter bid Price for tender : "+ConsoleColors.RESET);
        	  	    	 int offer = sc.nextInt();
						String msg = dao.placeBidAgainsTender(tid, offer);
						 System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT+msg+ConsoleColors.RESET);
						
					} catch (tenderException e) {
						// TODO Auto-generated catch block
						System.out.println(ConsoleColors.RED_BOLD_BRIGHT+e.getMessage()+ConsoleColors.RESET);
					}
    	  	    	 catch(InputMismatchException e) {
    	  	    		System.out.println(ConsoleColors.RED_BOLD_BRIGHT+"change something.."+ConsoleColors.RESET);
    	  	    		 }
    			 
    	  	    	 continue;
    			 }
    			 else if(pointer==3) {
    				 
//    				 =========================== View status of bid  =====================
    				 
    				 System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"Enter tender Id : "+ConsoleColors.RESET);
    	  	    	 int tid = sc.nextInt();
    	  	    	 
    	  	    	 try {
						String msg = dao.viewStatusofBid(tid);
						if(msg==null) {
							throw new NullPointerException("Tender Result is not anounced... ");
						}
						
						if(msg.equals("Accepted")) {
							 System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT+"your bid for tender Id: "+tid+" is get "+msg+ConsoleColors.RESET);
						}else if(msg.equals("Rejected")) {
							System.out.println(ConsoleColors.RED_BOLD_BRIGHT+"your bid for tender Id: "+tid+" is get "+msg+ConsoleColors.RESET);
						}else {
//							throw new NullPointerException("Tender is in process..");
//							 System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"Tender Result is not anounced... "+ConsoleColors.RESET);/
						}
					} catch (tenderException e) {
						// TODO Auto-generated catch block
//						throw new NullPointerException("Tender is in process..");
						System.out.println(ConsoleColors.RED_BOLD_BRIGHT+e.getMessage()+ConsoleColors.RESET);
					}
    				 catch (NullPointerException e) {
    					 System.out.println(ConsoleColors.RED_BOLD_BRIGHT+e.getMessage()+ConsoleColors.RESET);
    				 }
    				 
    				 
    				 continue;
    			 }
    			 else if(pointer==4) {
    				 
//    				=========================== View bid history ======================
    				 
    				 try {
    	    			  
    	     			 System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT+"OWN BID HISTORY."+ConsoleColors.RESET);
    	     			 
    	 				List<bids> list = dao.viewOwnBidHistory();
    	 				
    	 				for (bids ven : list) {
    	 					System.out.println(ConsoleColors.ORANGE+"Tender Id: "+ ven.getTid()+ConsoleColors.RESET);
    	 					System.out.println(ConsoleColors.ORANGE+"Vendor Id: "+ ven.getVid()+ConsoleColors.RESET);
    	 					System.out.println(ConsoleColors.ORANGE+"Vendor Name: "+ ven.getvName()+ConsoleColors.RESET);
    	 					System.out.println(ConsoleColors.ORANGE+"Bid Company: "+ ven.getCompany()+ConsoleColors.RESET);
    	 					System.out.println(ConsoleColors.ORANGE+"Bid Offer in Rs: "+ ven.getOffer()+ConsoleColors.RESET);
    	 					System.out.println(ConsoleColors.ORANGE+"Bid status: "+ ven.getBidStatus()+ConsoleColors.RESET);
    	 					System.out.println(ConsoleColors.BLACK_STRIKE+"*************************************1"+ConsoleColors.RESET);
    	 				}
    	 				
    	 			} catch (venderException e) {
    	 				// TODO Auto-generated catch block
    	 				System.out.println(ConsoleColors.RED_BOLD_BRIGHT+e.getMessage()+ConsoleColors.RESET);
    	 			}
    	     		 
    	     		 continue;
    				 
    			 }
    			 else {
    				 System.out.println(ConsoleColors.BLACK_UNDERLINED+ConsoleColors.RED_BOLD_BRIGHT+" ********** THANK YOU FOR USING THIS APPLICATION ********** "+ConsoleColors.RESET);
    				 break;
    			 }
    			 
    			 
    			 
    		 }
    		 
    	 }
    	 
    	 
    	 
     }
//     =================== exit value method =======================
     else  {
    	 System.out.println(ConsoleColors.BLACK_UNDERLINED+ConsoleColors.RED_BOLD_BRIGHT+" ********** THANK YOU FOR USING THIS APPLICATION ********** "+ConsoleColors.RESET);
     }
    
     
     

	}

}
