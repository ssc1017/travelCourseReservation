<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改线路信息</title>
  <%
     Integer id = (Integer)request.getAttribute("id");
     String name = (String)request.getAttribute("name");
     String type = (String)request.getAttribute("type");
     String detail = (String)request.getAttribute("detail");
     String bitmap = (String)request.getAttribute("bitmap");
     
   %>
</head>
<body>
      <form action="<%=request.getContextPath()%>/modifycourse.htm?id=<%=id%>" method="post">
	      <table>
	            <tr>
	                <td>线路名</td>        
	                <td><input name="name" value="<%=name %>" />
	                </td>     
	            </tr>
	            <tr>
	                <td>类型</td>        
	                <td><select name="type">
	                        <%
	                          if("国内".equals(type)){
	                       %>
	                       <option value="国内" selected>国内</option>
	                       <%
	                         }else{
	                        %>
	                        <option value="国内" >国内</option>
	                        <%
	                         }
	                         %>
	                       <%
	                          if("国外".equals(type)){
	                       %>
	                       <option value="国外" selected>国外</option>
	                       <%
	                         }else{
	                        %>
	                        <option value="国外" >国外</option>
	                        <%
	                         }
	                         %>
	                  </select></td>     
	            </tr>
	            <tr>
	                <td>线路描述</td>        
	                <td><input name="detail" value="<%=detail%>"/></td>     
	            </tr>
	            <tr>
	                <td>图片路径</td>        
	                <td><input name="bitmap" value="<%=bitmap%>"/></td>     
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