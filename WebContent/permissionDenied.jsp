<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%
	String appContext = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ appContext;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h5>非内置管理员，你没有权限访问 （PermissionDenied）</h5>
<br/><br/>
 	======非首页==============功能导航=====功能导航=====功能导航======功能导航=======功能导航==========
	<br />
	<a href="<%=basePath%>/ShowIndex">首页</a>
	<br />
	<a href="<%=basePath%>/ShowUserLongin">登录</a>
	<br />
	<a href="<%=basePath%>/ShowUserRegister">注册</a>
	<br />
</body>
</html>