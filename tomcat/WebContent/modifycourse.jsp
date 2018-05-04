<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改线路信息</title>
</head>
<body>
<%
		    Connection con = null;
	        Statement st = null;
	        ResultSet rs = null;
            String id = request.getParameter("id");
            String sql = "select *  from course where courseid='"+id+"'";
            
			try{
				
				Class.forName("com.mysql.cj.jdbc.Driver");
			}catch(ClassNotFoundException e)
			{
				e.printStackTrace();
				System.out.println("驱动加载错误");
			}
	        try {
	        	
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/androidproj?serverTimezone=UTC&useSSL=true","root","8733611314");
				st = con.createStatement();
				rs = st.executeQuery(sql);
				response.setContentType("text/html;charset=utf-8");
		        
		        if(rs.next()){
		            request.setAttribute("id",rs.getInt("courseid"));
		            request.setAttribute("name",rs.getString("coursename"));
		            request.setAttribute("type",rs.getString("coursetype"));
		            request.setAttribute("detail",rs.getString("coursedetail"));
		            request.setAttribute("bitmap",rs.getString("coursebitmap"));
		            RequestDispatcher rd = request.getRequestDispatcher("updatecourse.jsp");
		            rd.forward(request,response);
		        }else{
		        	out.println("没有找到id为"+id+"的线路记录");
		        }
		     } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
				    rs.close();
					st.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 %>
		 <br/>
</body>
</html>