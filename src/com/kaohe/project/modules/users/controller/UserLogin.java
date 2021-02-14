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

/**
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getSession().setAttribute("USER_TOKEN", "union-v");
		    response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        request.setCharacterEncoding("UTF-8");
			String username = request.getParameter("nickname");
			String password = request.getParameter("password");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/modules/users/login.jsp");
			if (username == null || username.equals("") ) {
				request.setAttribute("tips", "用户名为空");
				dispatcher.forward(request, response);
			}
			if (password == null || password.equals("") ) {
				request.setAttribute("tips", "密码为空");
				dispatcher.forward(request, response);
			}
			try {
				//开始注册用户
				
				UserBean userbean=userdao.getUser(username, password);				
				if(userbean!=null&&userbean.getId()>0) {
					request.setAttribute("tips", "登录成功,欢迎你:"+username);
					request.getRequestDispatcher("/WEB-INF/index.jsp").
					forward(request, response);
				}else {
					request.setAttribute("tips", "登录失败，用户或密码不存在！");
					dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("tips", "异常:"+e.getMessage());
				dispatcher.forward(request, response);
			}
	     

	}

}
