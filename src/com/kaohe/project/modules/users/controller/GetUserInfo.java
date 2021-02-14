package com.kaohe.project.modules.users.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.kaohe.project.modules.common.ResultBean;
import com.kaohe.project.modules.common.page.QueryResult;
import com.kaohe.project.modules.users.dao.IUsersDao;
import com.kaohe.project.modules.users.dao.bean.UserBean;
import com.kaohe.project.modules.users.dao.impl.UsersDaoImpl;

/**
 * 显示用户信息
 * 
 * @author liangrui
 * @date 2021-02-09
 */
@WebServlet("/GetUserInfo")
public class GetUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUsersDao userdao = new UsersDaoImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetUserInfo() {
		super();
	}

	/**
	 * @see 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		ResultBean rb = new ResultBean(true, "请求成功");
		String userId = request.getParameter("id");
		if (userId == null || "".equals(userId)) {
			rb.setMessage("id为空");
		} else {
			try {
				UserBean result = userdao.get(Integer.valueOf(userId));
				rb.setData(result);
			} catch (Exception e) {
				rb.setSuccess(false);
				rb.setMessage("系统异常!" + e.getMessage());
				e.printStackTrace();
			}
		}
		String json = JSON.toJSONString(rb);
		OutputStream out = response.getOutputStream();
		out.write(json.getBytes("UTF-8"));
		out.flush();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
