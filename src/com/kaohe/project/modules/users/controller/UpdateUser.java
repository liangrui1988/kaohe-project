package com.kaohe.project.modules.users.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.kaohe.project.modules.common.ResultBean;
import com.kaohe.project.modules.users.entity.User;
import com.kaohe.project.modules.users.service.IUsersService;
import com.kaohe.project.modules.users.service.impl.UsersServiceImpl;
import com.kaohe.project.sysconfig.utils.ency.MD5;

/**
 * 修改用户信息
 * 
 * @author liangrui
 * @date 2021-02-09
 */
@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUsersService usersService = new UsersServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 修改用户信息
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String password1 = request.getParameter("password");
		String password2 = request.getParameter("repeatPassword");
		String status = request.getParameter("status");
		ResultBean rb = new ResultBean(true, "请求成功");
		if (StringUtils.isBlank(email)) {
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
		if (StringUtils.isNotBlank(password1)) {
			if (!password1.equals(password2)) {
				rb.setSuccess(false);
				rb.setMessage("服务端验证有误");
				OutputStream out = response.getOutputStream();
				out.write(JSON.toJSONString(rb).getBytes("UTF-8"));
				out.flush();
				return;
			}
			user.setPassword(MD5.generateMd5(password1));
		}
		try {
			int count = usersService.update(user);
			if (count <= 0) {
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
