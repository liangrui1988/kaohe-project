package com.kaohe.project.modules.users.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaohe.project.modules.users.dao.IUsersDao;
import com.kaohe.project.modules.users.dao.bean.UserBean;
import com.kaohe.project.modules.users.dao.impl.UsersDaoImpl;
import com.kaohe.project.modules.users.entity.User;
import com.kaohe.project.sysconfig.utils.ency.MD5;

/**
*用户注册接口
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
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
		String code = request.getParameter("code");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/modules/users/register.jsp");
		// 验证
		if (username == null || username.equals("")) {
			request.setAttribute("tips", "服务端验证有误");
			dispatcher.forward(request, response);
		}
		if (email == null || email.equals("")) {
			request.setAttribute("tips", "服务端验证有误");
			dispatcher.forward(request, response);
		}
		if (password1 == null || password1.equals("") || !password1.equals(password2)) {
			request.setAttribute("tips", "服务端验证密码有误");
			dispatcher.forward(request, response);
		}
	
		//开始注册用户
		User user=new User();
		user.setUserName(username);
		user.setEmail(email);
		//md5和盐加密，为了好复制，去掉用户名的盐
		user.setPassword(MD5.generateMd5(password1));
		try {
			//用户是否存在
			UserBean getusre=userdao.getUser(username);
			if(getusre!=null&&getusre.getId()!=null&&getusre.getId()>0) {
				request.setAttribute("tips", "账号已存在！");
				dispatcher.forward(request, response);
			}
			int count=userdao.add(user);
			if(count<=0) {
				request.setAttribute("tips", "注册失败，请遇管理员联系");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("tips", "注册异常:"+e.getMessage());
			dispatcher.forward(request, response);
		}
     	request.setAttribute("tips", "注册成功，请登录");
		request.getRequestDispatcher("/WEB-INF/modules/users/login.jsp").
		forward(request, response);

	}

}
