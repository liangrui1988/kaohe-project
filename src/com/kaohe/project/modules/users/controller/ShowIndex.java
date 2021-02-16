package com.kaohe.project.modules.users.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaohe.project.modules.users.dao.bean.UserBean;

/**
 * 显示index页
 * @author liangrui
 * @date 2021-02-09
 */
@WebServlet("/ShowIndex")
public class ShowIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowIndex() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBean userinfo = (UserBean) session.getAttribute("userinfo");
		if (userinfo != null && userinfo.getUserName() != null) {
			request.setAttribute("tips", "当前用户:"+userinfo.getUserName() );
		}
		 RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/index.jsp"); 
			rd.forward(request, response);
	}

	/**
	 * 显示index页
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
