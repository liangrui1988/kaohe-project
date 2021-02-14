<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String appContext = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ appContext;
%>
<!-- JSTL 核心标签库 -->
 <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<%=basePath%>/resources/css/login_style.css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
</head>
<body>

<main>
  <form class="form" action="<%=appContext%>/UserLogin" method="post">
    <div class="form__cover"></div>
    <div class="form__loader">
      <div class="spinner active">

        <svg class="spinner__circular" viewBox="25 25 50 50">
          <circle class="spinner__path" cx="50" cy="50" r="20" fill="none" stroke-width="4" stroke-miterlimit="10"></circle>
        </svg>
      </div>
    </div>
    <div class="form__content">
      <h5>${tips}</h5>
      <h1>欢迎使用考核系统</h1>
      <div class="styled-input">
        <input type="text" class="styled-input__input" name="nickname">
        <div class="styled-input__placeholder"> <span class="styled-input__placeholder-text">用户名</span> </div>
        <div class="styled-input__circle"></div>
      </div>
      <div class="styled-input">
        <input type="password" class="styled-input__input" name="password">
        <div class="styled-input__placeholder"> <span class="styled-input__placeholder-text">密码</span> </div>
        <div class="styled-input__circle"></div>
      </div>
      <button type="submit" class="styled-button"> <span class="styled-button__real-text-holder"> <span class="styled-button__real-text">登录</span> <span class="styled-button__moving-block face"> <span class="styled-button__text-holder"> <span class="styled-button__text">登录</span> </span> </span><span class="styled-button__moving-block back"> <span class="styled-button__text-holder"> <span class="styled-button__text">登录</span> </span> </span> </span> </button>
    </div>
  </form>
</main>
<script  src="<%=basePath%>/resources/js/login_index.js"></script>

</body>
</html>