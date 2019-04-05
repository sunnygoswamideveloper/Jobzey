package com.jobzey.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jobzey.dao.UserDAO;
import com.jobzey.dao.UserDAOImpl;
import com.jobzey.model.UserDTO;


@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UserServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO userdao=new UserDAOImpl();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String submittype=request.getParameter("submit");
			UserDTO user=userdao.ValidateUserCredentials(username, password);
			if(submittype.equals("login") && user!=null && user.getFirstname()!=null) {
				request.setAttribute("message",user.getFirstname());
				request.getRequestDispatcher("welcome.jsp").forward(request, response);
			}else if(submittype.equals("register")) {
				user.setFirstname(request.getParameter("firstname"));
				user.setLastname(request.getParameter("lastname"));
				user.setPassword(password);
				user.setUsername(username);
				user.setAddress(request.getParameter("address"));
				user.setContact(request.getParameter("contact"));
				user.setJobroll(request.getParameter("jobroll"));
				userdao.insertUser(user);
				request.setAttribute("message","REGISTRATION DONE,PLEASE LOGIN !!!!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
			}else {
				request.setAttribute("message","DATA NOT FOUND, CLICK ON REGTER !!!!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
	}

}
