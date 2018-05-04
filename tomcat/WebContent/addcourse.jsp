<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加线路</title>
</head>
<body>
     <form action="<%=request.getContextPath()%>/addcourse.htm" method="post">
	      <table>
	      	    <tr>
	                <td>线路名</td>        
	                <td><input name="name"/></td>     
	            </tr>
	            <tr>
	                <td>类型</td>        
	                <td><select name="type">
	                       <option value="国内">国内</option>
	                       <option value="国外">国外</option>
	                  </select></td>     
	            </tr>
	            <tr>
	                <td>线路描述</td>        
	                <td><input name="detail"/></td>     
	            </tr>
	            <tr>
	                <td>图片路径</td>        
	                <td><input name="bitmap"/></td>     
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