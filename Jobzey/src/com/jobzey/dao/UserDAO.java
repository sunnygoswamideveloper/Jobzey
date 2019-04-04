package com.jobzey.dao;

import com.jobzey.model.UserDTO;

public interface UserDAO {
	public int insertUser(UserDTO user);
	
	public UserDTO ValidateUserCredentials(String username,String password);
}
