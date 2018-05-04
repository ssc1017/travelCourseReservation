<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加报名信息</title>
</head>
<body>
     <form action="<%=request.getContextPath()%>/addsignupfor.htm" method="post">
	      <table>
	      	    <tr>
	                <td>用户编号</td>        
	                <td><input name="touristid"/></td>     
	            </tr>
	            <tr>
	                <td>线路编号</td>        
	                <td><input name="courseid"/></td>     
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