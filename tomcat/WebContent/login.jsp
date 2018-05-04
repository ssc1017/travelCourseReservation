<%@ page language="java" contentType="text/html; charset=UTF-8"
   import="java.util.*"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理员登陆界面</title>
</head>
<body>
       <center>管理员登录<br/>
      		<form action="<%=request.getContextPath()%>/login.htm" method="post">
      			<table border="1">
      			     <tr>
   		             <td>登录名：</td>
   		             <td><input name="username"/></td>
   		         </tr>
   		         <tr>
   		             <td>密码：</td>
   		             <td><input name="passwd" type="password"/></td>
   		         </tr>
   		         <tr align="center">                                 
   		             <td colspan="2"><input type="submit" value="提交"/></td>
   		         </tr>
      			</table>
      		</form>
       </center>
</body>
</html>