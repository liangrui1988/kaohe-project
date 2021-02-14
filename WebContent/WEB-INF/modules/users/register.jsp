<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String appContext = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ appContext;
	String tips = request.getParameter("tips");
%>
<!-- JSTL 核心标签库 -->
 <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%-- <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link href="<%=basePath%>/resources/css/jq22.css" rel="stylesheet"
	type="text/css" />
<script src=" <%=basePath%>/resources/js/jquery/jquery-3.5.1.min.js"></script>
<script language='javascript' src=" <%=basePath%>/resources/js/jq22.js"></script>
<title>用户注册</title>
</head>
<body>

	<div class='body_main'>
		<!-- start main content -->
		<div class='index_box' style='margin-top: 20px;'>
			<div
				style="position: fixed; color: red; margin: 70px 0 0 450px; font-size: 16px; Z-index: 100; display: block;"
				id="hint"></div>
			<div class='box_title'>
				<div class='text_content'>
					<h1>用户注册 ${tips}</h1>
					<h3>
					</h3>
				</div>
			</div>
			<div class='box_main'>
				<div id="register" class="register">
					<form id="form" action="<%=appContext%>/UserRegister"
						method="post" onSubmit="return check();">
						<div id="form_submit" class="form_submit">
							<div class="fieldset">
								<div class="field-group">
									<label class="required title">用户名</label> <span
										class="control-group" id="mobile_input">
										<div class="input_add_long_background">
											<input class="register_input" type="text" id="mobile"
												name="username" maxLength="20" value=""
												onblur="__changeUserName('mobile');">
										</div>
									</span>
									<!--                 <label class="tips">仅用于发送服务开通与到期提醒以及紧急故障方便联系到您，绝对保密</label>
 -->
								</div>
								<div class="field-group">
									<label class="required title">邮箱</label> <span
										class="control-group" id="email_input">
										<div class="input_add_long_background">
											<input class="register_input" type="text" id="email"
												name="email" maxLength="50" value=""
												onblur="__changeUserName('email');">
										</div>
									</span> <label class="tips">请输入您常用的邮箱</label>
								</div>

								<div class="field-group">
									<label class="required title">设置密码</label> <span
										class="control-group" id="password1_input">
										<div class="input_add_long_background">
											<input class="register_input" type="password" id="password1"
												name="password1" maxLength="20" value=""
												onblur="checkPwd1(this.value);" />
										</div>
									</span> <label class="tips">请使用6~20个英文字母（区分大小写）、符号或数字</label>
								</div>


								<div class="field-group">
									<label class="required title">确认密码</label> <span
										class="control-group" id="password2_input">
										<div class="input_add_long_background">
											<input class="register_input" type="password" id="password2"
												name="password2" maxLength="20" value=""
												onblur="checkPwd2(this.value);" />
										</div>
									</span> <label class="tips">请使用6~20个英文字母（区分大小写）、符号或数字</label>
								</div>


								<div class="field-group">
									<label class="required title">邮箱验证码</label> <span
										class="control-group" id="code_input">
										<div class="input_add_background" style="margin-right: 15px;">
											<input class="register_input_ot" type="text" id="code"
												name="code" max_length="6" value=""
												onblur="checkAuthCode(this.value);">
										</div>
									</span> <label class="tips">若尝试多次仍无法接收到验证码，请您联系在线客服帮您开通账号</label>
								</div>
							</div>
						</div>
						<div id="div_submit" class="div_submit">
							<div class='div_submit_button'>
								<input id="submit" type="submit" value="注册"
									class='button_button disabled'>
							</div>
						</div>
					</form>
				</div>
				<script type="text/javascript">
					function __changeUserName(of) {
						var username = $('#' + of).val();
						if (of == 'email') {
							if (username.search(/^[\w\.+-]+@[\w\.+-]+$/) == -1) {
								showTooltips('email_input', '请输入正确的Email地址');
								return;
							}
						} else {
							/* 	  if(username=='' || !isMobilePhone(username)) {
							 showTooltips('mobile_input','请输入正确的手机号码');
							 return;
							 } */

							if (username == '' || username.length > 20) {
								showTooltips('mobile_input', '用户名不能为空,长度不能大于20');
								return;
							}
						}
					}
					function checkPwd1(pwd1) {
						if (pwd1.search(/^.{6,20}$/) == -1) {
							showTooltips('password1_input', '密码为空或位数太少');
						} else {
							hideTooltips('password1_input');
						}
					}

					function checkPwd2(pwd1) {
						if (pwd1.search(/^.{6,20}$/) == -1) {
							showTooltips('password2_input', '密码为空或位数太少');
						} else if ($('#password1').val() != $('#password2')
								.val()) {
							showTooltips('password2_input', '密码不一致');

						} else {
							hideTooltips('password1_input');
						}
					}

					function checkEmail(email) {
						if (email.search(/^.+@.+$/) == -1) {
							showTooltips('email_input', '邮箱格式不正确');
						} else {
							hideTooltips('email_input');
						}
					}

					function checkAuthCode(authcode) {
						if (authcode == '' || authcode.length != 6) {
							showTooltips('code_input', '验证码不正确');
						} else {
							hideTooltips('code_input');
						}
					}

					function check() {
						hideAllTooltips();
						var ckh_result = true;
						if ($('#email').val() == '') {
							showTooltips('email_input', '邮箱不能为空');
							ckh_result = false;
						}
						if ($('#password1').val() == '') {
							showTooltips('password1_input', '密码不能为空');
							ckh_result = false;
						}

						if ($('#password2').val() == '') {
							showTooltips('password1_input', '密码不能为空');
							ckh_result = false;
						}

						if ($('#password1').val() != $('#password2').val()) {
							showTooltips('password1_input', '密码不一致');
							ckh_result = false;
						}

						if ($('#mobile').val() == '') {
							showTooltips('mobile_input', '用户名不能为空');
							ckh_result = false;
						}
						if ($('#code').val() == ''
								|| $('#code').val().length != 6) {
							showTooltips('code_input', '验证码不正确');
							ckh_result = false;
						}
						if ($('#verify').attr('checked') == false) {
							showTooltips('checkbox_input',
									'对不起，您不同意Webluker的《使用协议》无法注册');
							ckh_result = false;
						}
						return ckh_result;
					}
					function checkMobilePhone(telphone) {
						if (telphone == '' || !isMobilePhone(telphone)) {
							showTooltips('mobile_input', '请输入正确的手机号码');
						} else {
							hideTooltips('mobile_input');
						}
					}
					function isMobilePhone(value) {
						if (value.search(/^(\+\d{2,3})?\d{11}$/) == -1)
							return false;
						else
							return true;
					}
				</script>
			</div>
			<div class='box_bottom'></div>
		</div>
	</div>
</body>
</html>