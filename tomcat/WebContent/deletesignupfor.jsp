<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>删除报名信息</title>
</head>
<body>
	<BODY>
		<%
		    Connection con = null;
	        Statement st = null;
	        try {
	        	Class.forName("com.mysql.cj.jdbc.Driver");
	        	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/androidproj?serverTimezone=UTC&useSSL=true","root","8733611314");
				st = con.createStatement();
				request.setCharacterEncoding("UTF-8");
                String id1 = request.getParameter("touristid");
                String id2 = request.getParameter("courseid");
                String sql = "delete from touristcourse where touristid= '"+id1+"' and courseid= '"+id2+"'";
		        int  result = st.executeUpdate(sql); 
		     } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				//记住关闭连接
				try {
					st.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 %>
		 <a href="signupfor.jsp">删除完成，点击返回报名信息列表</a>
		 <br/>
	</BODY>
</body>
</html>