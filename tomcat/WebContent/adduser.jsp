<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加用户</title>
</head>
<body>
     <form action="<%=request.getContextPath()%>/adduser.htm" method="post">
	      <table>
	      	    <tr>
	                <td>账号</td>        
	                <td><input name="act"/></td>     
	            </tr>
	            <tr>
	                <td>密码</td>        
	                <td><input name="pw"/></td>     
	            </tr>
	            <tr>
	                <td>姓名</td>        
	                <td><input name="name"/></td>     
	            </tr>
	            <tr>
	                <td>性别</td>        
	                <td><select name="gender">
	                       <option value="男">男</option>
	                       <option value="女">女</option>
	                  </select></td>     
	            </tr>
	            <tr>
	                <td>年龄</td>        
	                <td><input name="age"/></td>     
	            </tr>
	            <tr>
	                <td>电话</td>        
	                <td><input name="tele"/></td>     
	            </tr>
	            <tr>
	                <td>邮箱</td>        
	                <td><input name="mail"/></td>     
	            </tr>
	            <tr>
	                <td colspan="2">
	                   <input type="submit" value="提交"/>
	                </td>
	            </tr>
	       </table>
      </form>
</body>
</html>