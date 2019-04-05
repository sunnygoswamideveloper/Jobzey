package com.jobzey.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jobzey.dao.UserDAO;
import com.jobzey.dao.UserDAOImpl;
import com.jobzey.model.UserDTO;


//UserLoginServlet is for Login validation and generate cookie for login users. 


@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UserLoginServlet() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		
		
		UserDAO sd=new UserDAOImpl();
		
		UserDTO user=sd.ValidateUserCredentials(username, password);
		
		if(user!=null ) {
			
			// valid creds 
			request.setAttribute("message",user.getFirstname());
			
			// Creating new session for valid user 
			HttpSession session = request.getSession(true);
			
			// Setting session variable 
			
			session.setAttribute("username", user.getUsername());
			
			session.setAttribute("firstname", user.getFirstname());
			
			
			// redirectig to welcome page 
			response.sendRedirect("index.jsp");
		}else {
			
			// invalid creds 
			request.setAttribute("message","INVALID CREDINTIALS");
			// adding message 
			
			//rdiret to login 
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		
	
	
	
	}

}
