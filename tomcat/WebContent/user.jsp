<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户信息列表</title>
</head>
<body>
		<center>
			<h4>用户信息列表</h4>
		</center>
		<% 
		    request.setCharacterEncoding("UTF-8");
		    String name = request.getParameter("name");
		    String gender = request.getParameter("gender");

			Connection con = null;
	        Statement st = null;
	        ResultSet rs = null;
	        
	        String sql = "select * from tourist where 1=1 ";
	        if(name!=null&&!"".equals(name)){
	        	sql+="and touristname like '%"+name+"%'";
	        }
	        if(gender!=null&&!"".equals(gender)){
	        	sql+="and touristgender = '"+gender+"'";
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
		 <form action="user.jsp" method="post">
			 <table>
			     <tr>
			         <td>姓名：</td>
			         <td><input name="name"/></td>
			         <td>性别：</td>
			         <td><select name="gender" style="width:100">
			                <option value="">无限制</option>
			                <option value="男">男</option>
			                <option value="女">女</option>
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
			    <td>编号</td>
				<td>账号</td>
				<td>密码</td>
				<td>姓名</td>
				<td>性别</td>
				<td>年龄</td>
				<td>电话</td>
				<td>邮箱</td>
				<td>操作</td>
			</tr>
		    <%
		        //遍历结果集ResultSet
				while(rs.next()){
					int id = rs.getInt("touristid");
					String stract = rs.getString("touristact");
					String strpw = rs.getString("touristpw");
					String strname  = rs.getString("touristname");
					String strgender = rs.getString("touristgender"); 
					int strage = rs.getInt("touristage");
					String strtele = rs.getString("touristtele");
					String strmail = rs.getString("touristmail"); 
					out.println("<tr>");
					out.println("<td >"+id+"</td>");
					out.println("<td >"+stract+"</td>");
					out.println("<td >"+strpw+"</td>");
					out.println("<td >"+strname+"</td>");
					out.println("<td >"+strgender+"</td>");	 
					out.println("<td >"+strage+"</td>");
					out.println("<td >"+strtele+"</td>");
					out.println("<td >"+strmail+"</td>");
					out.println("<td><a href=\"modifyuser.jsp?id="+id+"\">修改</a>&nbsp;&nbsp;"+
					"<a href=\"deleteuser.jsp?id="+id+"\" onclick=\"return confirm('确定删除该记录？')\">删除</a></td>");
				    out.println("</tr>");
				}
		     %>
		</table>
		<%
		out.println("<a href=\""+request.getContextPath()+"/adduser.jsp\">点击添加用户</a>");
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