package com.kaohe.project.modules.users.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.kaohe.project.modules.common.ResultBean;
import com.kaohe.project.modules.users.dao.IUsersDao;
import com.kaohe.project.modules.users.dao.impl.UsersDaoImpl;
import com.kaohe.project.modules.users.entity.User;

/**
 * 修改用户信息
 * 
 * @author liangrui
 * @date 2021-02-09
 */
@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUsersDao userdao = new UsersDaoImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUser() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String status = request.getParameter("status");
        ResultBean rb=new ResultBean(true,"请求成功");
		if (email == null || email.equals("")) {
			rb.setSuccess(false);
			rb.setMessage("服务端验证有误");
			OutputStream out = response.getOutputStream();
			out.write(JSON.toJSONString(rb).getBytes("UTF-8"));
			out.flush();
			return;
		}

		User user = new User();
		user.setId(Integer.valueOf(id));
		user.setEmail(email);
		user.setStatus(Integer.valueOf(status));
		if (password1 != null && !password1.equals("")) {
			if (!password1.equals(password2)) {
				rb.setSuccess(false);
				rb.setMessage("服务端验证有误");
				OutputStream out = response.getOutputStream();
				out.write(JSON.toJSONString(rb).getBytes("UTF-8"));
				out.flush();
				return;
			} 
			user.setPassword(password1);
		}
		try {
			int count=userdao.update(user);
			if(count<=0) {
				rb.setSuccess(false);
				rb.setMessage("修改不成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rb.setSuccess(false);
			rb.setMessage("修改异常:" + e.getMessage());
		}		
		OutputStream out = response.getOutputStream();
		out.write(JSON.toJSONString(rb).getBytes("UTF-8"));
		out.flush();		
	}

}
