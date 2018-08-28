package com.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {
	

	private static Connection con;
	
	public static Connection getMyConnection(){
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=DUMMY", "sa", "sa123");
			System.out.println("Connection succesful");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {    
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
		
	}
	public static void closeMyConnection(){
		
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
