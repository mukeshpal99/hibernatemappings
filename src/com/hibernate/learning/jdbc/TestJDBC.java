package com.hibernate.learning.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {
	
	public static void main(String[] args)
	{
		
		String jdbcUrl ="jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false";
		String user = "hbstudent";
		String password= "hbstudent";
		
		try
		{
			System.out.println("Connecting to Database: "+jdbcUrl);
			
			Connection mycon = DriverManager.getConnection(jdbcUrl, user, password);
			
			System.out.println("Connection successsful");
			
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
