package com.hustlecrafts.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {
	public static void main(String[] args) {
		
		String jdbcURL = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false";
		String username = "hbstudent";
		String password = "hbstudent";
		
		try {
			System.out.println("Connecting to database: " + jdbcURL);
			
			Connection connection = DriverManager.getConnection(jdbcURL, username, password);
			
			System.out.println("Connection successful!!!");
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}
}
