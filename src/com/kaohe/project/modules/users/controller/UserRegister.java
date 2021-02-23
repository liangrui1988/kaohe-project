package com.kaohe.project.modules.users.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.EmailException;

import com.kaohe.project.modules.users.dao.IUsersDao;
import com.kaohe.project.modules.users.dao.bean.UserBean;
import com.kaohe.project.modules.users.dao.impl.UsersDaoImpl;
import com.kaohe.project.modules.users.entity.User;
import com.kaohe.project.sysconfig.utils.EmailUtil;
import com.kaohe.project.sysconfig.utils.ency.MD5;

/**
 * 用户注册接口
 * 
 * @author liangrui
 * @date 2021-02-09
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUsersDao userdao = new UsersDaoImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRegister() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/noGet.jsp");
//		dispatcher.forward(request, response);
		doPost(request, response);
	}

	/**
	 * 用户注册接口
	 *  @param username 名称
	 *  @param password1,password2 密码
	 *  @param email 邮箱
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/modules/users/register.jsp");
		// 验证
		if (StringUtils.isBlank(username)) {
			request.setAttribute("tips", "服务端验证有误");
			dispatcher.forward(request, response);
		}
		if (StringUtils.isBlank(email)) {
			request.setAttribute("tips", "服务端验证有误");
			dispatcher.forward(request, response);
		}
		if (StringUtils.isBlank(password1) || !password1.equals(password2)) {
			request.setAttribute("tips", "服务端验证密码有误");
			dispatcher.forward(request, response);
		}
		// 开始注册用户
		User user = new User();
		user.setUserName(username);
		user.setEmail(email);
		// md5和盐加密，为了好复制，去掉用户名的盐
		user.setPassword(MD5.generateMd5(password1));
		try {
			// 用户是否存在
			UserBean getusre = userdao.getUser(username);
			if (getusre != null && getusre.getId() != null && getusre.getId() > 0) {
				request.setAttribute("tips", "账号已存在！");
				dispatcher.forward(request, response);
			}
			// 发送验证码链接到邮箱
			String code = username + "_" + request.getSession().getId() + "_"
					+ UUID.randomUUID().toString().substring(0, 4);
			String md5code = MD5.generateMd5(code);
			user.setCode(md5code);
			StringBuffer sb = new StringBuffer();
			sb.append("<a href='");
			sb.append("http://");
			sb.append(request.getServerName());
			sb.append(":");
			sb.append(request.getServerPort());
			sb.append(request.getContextPath());
			sb.append("/EmailVerify");
			sb.append("?code=");
			sb.append(md5code);
			sb.append("'>"+username+":请点击进行验证激活，若邮箱拦截，请复制链接单独打开验证</a>");
			System.out.print(sb.toString());
			try {
				EmailUtil.sendContextEmailHTML(sb.toString(), "考核系统邮箱注册验证", email, email);
			} catch (EmailException e) {
				e.printStackTrace();
				request.setAttribute("tips", "验证邮件发生失败！请检查邮箱是否正确或联系管理员");
				dispatcher.forward(request, response);
				return;
			}
			int count = userdao.add(user);
			if (count <= 0) {
				//这里需要写撤销邮件逻辑，暂不
				request.setAttribute("tips", "注册失败，请遇管理员联系");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("tips", "注册异常:" + e.getMessage());
			dispatcher.forward(request, response);
		}
		request.setAttribute("tips", "注册成功，请登录");
		request.getRequestDispatcher("/WEB-INF/modules/users/login.jsp").forward(request, response);

	}

}
