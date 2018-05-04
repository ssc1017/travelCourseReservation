<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<center>
			<h4>欢迎登陆数据库管理系统，<%=(String)session.getAttribute("account")%></h4>
		</center>
		
		<%
		out.println("<a href=\""+request.getContextPath()+"/user.jsp\">用户信息管理</a>");%>
		</br>
		<%
		out.println("<a href=\""+request.getContextPath()+"/course.jsp\">线路信息管理</a>");%>
		</br>
		<%
		out.println("<a href=\""+request.getContextPath()+"/signupfor.jsp\">报名信息管理</a>");%>
</body>
</html>