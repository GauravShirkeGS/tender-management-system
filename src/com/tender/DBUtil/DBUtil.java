package com.tender.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtil {

	private static String driverName;
	private static String url;
	private static String username;
	private static String password;
	
	static {
		ResourceBundle rb = ResourceBundle.getBundle("dbDetailes");	
		driverName = rb.getString("db.drivarName");
		url = rb.getString("db.url");
	    username = rb.getString("db.username");
		password = rb.getString("db.password");
		
	}
	
	
	public static Connection provideConnection() {
		Connection conn = null;
		
		
		try {
			Class.forName(driverName);	
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
//		String url = "jdbc:mysql://localhost:3306/Tenderdb";
		try {
//			System.out.println(driverName);
//			System.out.println(url);
//			System.out.println(username);
//			System.out.println(password);
			conn = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		return conn;
	}
}
