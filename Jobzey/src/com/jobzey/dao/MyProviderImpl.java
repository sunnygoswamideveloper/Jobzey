package com.jobzey.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyProviderImpl implements MyProvider {
		static Connection con=null;
			public static Connection getCon() {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con=DriverManager.getConnection(conurl,username,password);
			
				}catch(Exception e) {
					System.out.println(e);
				}
		return con;
	}
}
