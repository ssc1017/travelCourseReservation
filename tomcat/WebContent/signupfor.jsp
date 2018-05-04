<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>报名信息列表</title>
</head>
<body>
		<center>
			<h4>报名信息列表</h4>
		</center>
		<% 
		    request.setCharacterEncoding("UTF-8");
		    String id1 = request.getParameter("id1");
		    String id2 = request.getParameter("id2");

			Connection con = null;
	        Statement st = null;
	        ResultSet rs = null;
	        
	        String sql = "select * from touristcourse where 1=1 ";
	        if(id1!=null&&!"".equals(id1)){
	        	sql+="and touristid = '"+id1+"'";
	        }
	        if(id2!=null&&!"".equals(id2)){
	        	sql+="and courseid = '"+id2+"'";
	        }
	        
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
		 %>
		 <form action="signupfor.jsp" method="post">
			 <table>
			     <tr>
			         <td>用户编号：</td>
			         <td><input name="id1"/></td>
			         <td>线路编号：</td>
			         <td><input name="id2"/></td>
			     </tr>
			     <tr>
			         <td>
			             <input type="submit" value="提交">
			             <input type="reset" value="重置">
			         </td>
			     </tr>
			 </table>
		 </form>
		 <br/>
		 <br/>
		<table border="1" width="100%" cellpadding="2" cellspacing="1">
			<tr>
			    <td>用户编号</td>
				<td>线路编号</td>
				<td>操作</td>
			</tr>
		    <%
		        //遍历结果集ResultSet
				while(rs.next()){
					int touristid = rs.getInt("touristid");
					int courseid = rs.getInt("courseid"); 
					out.println("<tr>");
					out.println("<td >"+touristid+"</td>");
					out.println("<td >"+courseid+"</td>");
					out.println("<td><a href=\"deletesignupfor.jsp?touristid="+touristid+"&courseid="+courseid+"\" onclick=\"return confirm('确定删除该记录？')\">删除</a></td>");
				    out.println("</tr>");
				}
		     %>
		</table>
		<%
		out.println("<a href=\""+request.getContextPath()+"/addsignupfor.jsp\">点击添加报名信息</a>");
		out.println("</br><a href=\""+request.getContextPath()+"/index.jsp\">返回</a>");
		     } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					if(rs!=null)rs.close();
					st.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 %>
</body>
</html>