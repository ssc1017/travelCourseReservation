<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>线路信息列表</title>
</head>
<body>
		<center>
			<h4>线路信息列表</h4>
		</center>
		<% 
		    request.setCharacterEncoding("UTF-8");
		    String name = request.getParameter("name");
		    String type = request.getParameter("type");

			Connection con = null;
	        Statement st = null;
	        ResultSet rs = null;
	        
	        String sql = "select * from course where 1=1 ";
	        if(name!=null&&!"".equals(name)){
	        	sql+="and coursename like '%"+name+"%'";
	        }
	        if(type!=null&&!"".equals(type)){
	        	sql+="and coursetype = '"+type+"'";
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
		 <form action="course.jsp" method="post">
			 <table>
			     <tr>
			         <td>线路名：</td>
			         <td><input name="name"/></td>
			         <td>类型：</td>
			         <td><select name="type" style="width:100">
			                <option value="">无限制</option>
			                <option value="国内">国内</option>
			                <option value="国外">国外</option>
			           </select></td>
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
			    <td>线路编号</td>
				<td>线路名</td>
				<td>类型</td>
				<td>线路描述</td>
				<td>图片路径</td>
				<td>操作</td>
			</tr>
		    <%
		        //遍历结果集ResultSet
				while(rs.next()){
					int id = rs.getInt("courseid");
					String strname = rs.getString("coursename");
					String strtype = rs.getString("coursetype");
					String strdetail = rs.getString("coursedetail"); 
					String strbitmap = rs.getString("coursebitmap"); 
					out.println("<tr>");
					out.println("<td >"+id+"</td>");
					out.println("<td >"+strname+"</td>");
					out.println("<td >"+strtype+"</td>");
					out.println("<td >"+strdetail+"</td>");	 
					out.println("<td >"+strbitmap+"</td>");
					out.println("<td><a href=\"modifycourse.jsp?id="+id+"\">修改</a>&nbsp;&nbsp;"+
					"<a href=\"deletecourse.jsp?id="+id+"\" onclick=\"return confirm('确定删除该记录？')\">删除</a></td>");
				    out.println("</tr>");
				}
		     %>
		</table>
		<%
		out.println("<a href=\""+request.getContextPath()+"/addcourse.jsp\">点击添加线路</a>");
		out.println("</br><a href=\""+request.getContextPath()+"/index.jsp\">返回</a>");
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
</body>
</html>