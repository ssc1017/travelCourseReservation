package com.androidproj;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "adduserServlet", urlPatterns = { "/adduser.htm" })
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public AddUserServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        String act = request.getParameter("act");
        String pw = request.getParameter("pw");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String age = request.getParameter("age");
        String tele = request.getParameter("tele");
        String mail = request.getParameter("mail");
        String sql = "insert into tourist(touristact,touristpw,touristname,touristgender,touristage,touristtele,touristmail) "+
                     "values('"+act+"','"+pw+"','"+name+"','"+gender+"','"+Integer.parseInt(age)+"','"+tele+"','"+mail+"')";
        Connection con = null;
        Statement st = null;
        int  result = 0;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/androidproj?serverTimezone=UTC&useSSL=true","root","8733611314");
			st = con.createStatement();
			result = st.executeUpdate(sql);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out .println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>列出人员信息表</TITLE></HEAD>");
			out.println("  <BODY>");
			out.println("");
			out.println("<a href=\""+request.getContextPath()+"/user.jsp\">插入完成，点击返回用户信息列表</a>");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}

	public void init() throws ServletException {
	}

}
