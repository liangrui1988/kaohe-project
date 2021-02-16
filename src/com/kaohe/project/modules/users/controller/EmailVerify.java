package com.kaohe.project.modules.users.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaohe.project.modules.users.dao.IUsersDao;
import com.kaohe.project.modules.users.dao.impl.UsersDaoImpl;

/**
 * 邮箱验证激活
 * 
 * @author liangrui
 * @date 2021-02-15
 */
@WebServlet("/EmailVerify")
public class EmailVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUsersDao userdao = new UsersDaoImpl();

	/**
	 * 邮箱验证激活
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String code = request.getParameter("code");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/modules/users/register.jsp");
		try {
			int count = userdao.updateByCode(code);
			if (count <= 0) {
				request.setAttribute("tips", "激活失败！末能找到对应的code");
				dispatcher.forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("tips", "激活异常，请联系管理员");
			dispatcher.forward(request, response);
			return;
		}
		request.setAttribute("tips", "激活成功，请您登录！");
		request.getRequestDispatcher("/WEB-INF/modules/users/login.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
