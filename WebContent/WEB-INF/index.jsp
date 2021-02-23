<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String appContext = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ appContext;
%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>考核首页</title>
</head>
<body>
	<a href="<%=basePath%>/Logout">退出登录</a>
	<br /> =====================欢迎来到考核首页=====================
	<br /> =====================欢迎来到考核首页=====================
	<br /> =====================欢迎来到考核首页=====================
	<br /> ${tips}
	<br /> 功能导航
	<br />
	<a href="<%=basePath%>/ShowIndex">首页</a>
	<br />
	<a href="<%=basePath%>/ShowUserLongin">登录</a>
	<br />
	<a href="<%=basePath%>/ShowUserRegister">注册</a>
	<br />
	<a href="<%=basePath%>/ShowManager">管理</a>
	<br />
	<script type="text/javascript">
		
	</script>
	=====================欢迎来到考核首页=====================
	<br />
</body>
</html>