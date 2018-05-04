package com.androidproj;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "modifycourseServlet", urlPatterns = { "/modifycourse.htm" })
public class ModifyCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * Destruction of the servlet. <br>
	 */
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
		String id = request.getParameter("id");
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String detail = request.getParameter("detail");
        String bitmap = request.getParameter("bitmap");
        Connection con = null;
        Statement st = null;
        int  result = 0;
        
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/androidproj?serverTimezone=UTC&useSSL=true","root","8733611314");
			st = con.createStatement();
			result = st.executeUpdate("update course set coursename='"+name+"',coursetype='"+type+"',coursedetail='"+detail+"',coursebitmap='"+bitmap + "' where courseid='"+Integer.parseInt(id)+"'");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>�޸����</TITLE></HEAD>");
			out.println("  <BODY>");
			out.println("");
			out.println("<a href=\""+request.getContextPath()+"/course.jsp\">�޸����,���������·��Ϣ�б�</a>");
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
				con.close();
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
	}
}
