package com.kaohe.project.modules.users.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaohe.project.modules.users.dao.IUsersDao;
import com.kaohe.project.modules.users.dao.bean.UserBean;
import com.kaohe.project.modules.users.dao.impl.UsersDaoImpl;
import com.kaohe.project.sysconfig.utils.ency.MD5;
import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;

/**
 * 用户登录接口
 * 
 * @author liangrui
 * @date 2021-02-09
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IUsersDao userdao = new UsersDaoImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/noGet.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String username = request.getParameter("nickname");
		String password = request.getParameter("password");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/modules/users/login.jsp");
		try {
			if (username == null || username.equals("")) {
				request.setAttribute("tips", "用户名为空");
				dispatcher.forward(request, response);
				return;
			}
			if (password == null || password.equals("")) {
				request.setAttribute("tips", "密码为空");
				dispatcher.forward(request, response);
				return;
			}
			String userCaptchaResponse = request.getParameter("jcaptcha");
			boolean captchaPassed = SimpleImageCaptchaServlet.validateResponse(request, userCaptchaResponse);
			if (!captchaPassed) {
				request.setAttribute("tips", "验证码有误");
				dispatcher.forward(request, response);
				return;
			}
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(60 * 2);
			// 用户登录错3次，禁用用户
			Integer loginCount = (Integer) session.getAttribute("loginCount_" + username);
			if (loginCount == null) {
				loginCount = 0;
			}
			if (loginCount > 3) {
				request.setAttribute("tips", "用户已输错3次，2分钟内不许再试登录，请遇管理员联系！");
				dispatcher.forward(request, response);
				return;
			}
			UserBean userbean = userdao.getUser(username, MD5.generateMd5(password));
			if (userbean == null || userbean.getId() == null || userbean.getId() <= 0) {
				session.setAttribute("loginCount_" + username, loginCount + 1);
				if (loginCount == 3) {
					// 若异常3次，这里把用户状态禁用
					userdao.update(username, 1);
					request.setAttribute("tips", "用户输错3次，现在已被系统设禁用，请遇管理员联系！");
					dispatcher.forward(request, response);
					return;
				}
				request.setAttribute("tips", "登录失败，用户或密码不存在！");
				dispatcher.forward(request, response);
			} else {
				if (userbean.getStatus()== 1) {
					request.setAttribute("tips", "用户已被禁用，请遇管理员联系！");
					dispatcher.forward(request, response);
					return;
				}
				if (userbean.getStatus()==-1) {
					request.setAttribute("tips", "用户还没有进行邮箱验证，请完登录邮箱打开链接进行验证！");
					dispatcher.forward(request, response);
					return;
				}
				// 生成token 写session
				UserBean userinfo = userdao.get(userbean.getId());
				System.out.println("session set userinfo=" + userinfo);
				session.setAttribute("userinfo", userinfo);
				session.setMaxInactiveInterval(60 * 60 * 2);// session有效期60秒*60分*2小时
				request.setAttribute("tips", "登录成功,欢迎你:" + username);
				request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("tips", "异常:" + e.getMessage());
			dispatcher.forward(request, response);
		}

	}

}
