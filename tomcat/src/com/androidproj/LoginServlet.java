package com.androidproj;

import java.io.IOException;


import java.sql.*;
//import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
  

@WebServlet(name = "loginServlet", urlPatterns = { "/login.htm" })
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("username");
		String passwd = request.getParameter("passwd");
		
		Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        
		try{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			System.out.println("驱动加载错误");
		}
        
		try
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/androidproj?serverTimezone=UTC&useSSL=true","root","8733611314");
			st = con.createStatement();
			rs = st.executeQuery("select * from admin where act = '"+ userName +"'");
			response.setContentType("text/html;charset=utf-8");
			
			if(rs.next()){
				String act=rs.getString("act");
				String pw=rs.getString("pw");
				if(passwd.equals(pw))
				{
					request.getSession().setAttribute("account", act);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				else{
					request.setAttribute("status","用户名或密码错误");
					request.getRequestDispatcher("login.jsp").forward(request, response);
					response.sendRedirect(request.getContextPath()+"/login.jsp");
				}
			}
			else
			{request.setAttribute("status","用户名或密码错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
		catch(SQLException e){e.printStackTrace();}

		finally{
			try{
				con.close();
				st.close();
				rs.close();
			}
			catch(SQLException e){e.printStackTrace();}
		}
		
	}
}
