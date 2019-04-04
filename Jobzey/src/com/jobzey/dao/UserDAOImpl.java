package com.jobzey.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jobzey.model.UserDTO;

public class UserDAOImpl implements UserDAO {

	static Connection con=null;
	
	PreparedStatement ps=null;
@Override
public int insertUser(UserDTO user) {
	
	int status=1;
	try {
		
		con=MyProviderImpl.getCon();
		ps=con.prepareStatement("insert into jobzeydatabase.usersrecord (firstname,lastname,username,password,address,contact,jobroll) values(?,?,?,?,?,?,?)");
		ps.setString(1, user.getFirstname());
		ps.setString(2, user.getLastname());
		ps.setString(3, user.getUsername());
		ps.setString(4, user.getPassword());
		ps.setString(5, user.getAddress());
		ps.setString(6, user.getContact());
		ps.setString(7,user.getJobroll());
		
		status=ps.executeUpdate();
		con.close();
	}catch(Exception e) {
		System.out.println(e);
	}
	return status;
}
 public UserDTO ValidateUserCredentials(String username,String password) {
	// Declaring student
			UserDTO user;
			
			// Defining student 
			
			user=new UserDTO();
			
				try {
					
					con=MyProviderImpl.getCon();
					
					ps=con.prepareStatement("select * from jobzeydatabase.usersrecord where username=? and password=?;");
					
					ps.setString(1,username);
					
					ps.setString(2, password);
					
					ResultSet rs=ps.executeQuery();
					while(rs.next()) {
						user.setFirstname(rs.getString(1));
						user.setLastname(rs.getString(2));
						user.setUsername(rs.getString(3));
						user.setPassword(rs.getString(4));
						user.setAddress(rs.getString(5));
						user.setContact(rs.getString(6));
						user.setJobroll(rs.getString(7));
					}
					rs.close();
				}catch(Exception e) {
					System.out.println(e);
				}
			return user;
		}

}
