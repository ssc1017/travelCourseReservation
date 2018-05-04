<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改用户信息</title>
  <%
     Integer id = (Integer)request.getAttribute("id");
     String act = (String)request.getAttribute("act");
     String pw = (String)request.getAttribute("pw");
     String name = (String)request.getAttribute("name");
     String gender = (String)request.getAttribute("gender");
     Integer age = (Integer)request.getAttribute("age");
     String tele = (String)request.getAttribute("tele");
     String mail = (String)request.getAttribute("mail");
     
   %>
</head>
<body>
      <form action="<%=request.getContextPath()%>/modifyuser.htm?id=<%=id%>" method="post">
	      <table>
	      	    <tr>
	                <td>账号</td>        
	                <td><input name="act" value="<%=act %>" />
	                </td>     
	            </tr>
	            <tr>
	                <td>密码</td>        
	                <td><input name="pw" value="<%=pw %>" />
	                </td>     
	            </tr>
	            <tr>
	                <td>姓名</td>        
	                <td><input name="name" value="<%=name %>" />
	                </td>     
	            </tr>
	            <tr>
	                <td>性别</td>        
	                <td><select name="gender">
	                        <%
	                          if("男".equals(gender)){
	                       %>
	                       <option value="男" selected>男</option>
	                       <%
	                         }else{
	                        %>
	                        <option value="男" >男</option>
	                        <%
	                         }
	                         %>
	                       <%
	                          if("女".equals(gender)){
	                       %>
	                       <option value="女" selected>女</option>
	                       <%
	                         }else{
	                        %>
	                        <option value="女" >女</option>
	                        <%
	                         }
	                         %>
	                  </select></td>     
	            </tr>
	            <tr>
	                <td>年龄</td>        
	                <td><input name="age" value="<%=age%>"/></td>     
	            </tr>
	            <tr>
	                <td>电话</td>        
	                <td><input name="tele" value="<%=tele%>"/></td>     
	            </tr>
	            <tr>
	                <td>邮箱</td>        
	                <td><input name="mail" value="<%=mail%>"/></td>     
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