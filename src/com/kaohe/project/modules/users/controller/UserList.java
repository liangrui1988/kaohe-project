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
import com.kaohe.project.modules.common.page.QueryResult;
import com.kaohe.project.modules.users.dao.IUsersDao;
import com.kaohe.project.modules.users.dao.bean.UserBean;
import com.kaohe.project.modules.users.dao.impl.UsersDaoImpl;
import com.kaohe.project.modules.users.service.IUsersService;
import com.kaohe.project.modules.users.service.impl.UsersServiceImpl;

/**
 * 用户列表管理页面
 * @author liangrui
 * @date 2021-02-09
 */
@WebServlet("/UserList")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUsersService usersService = new UsersServiceImpl();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        ResultBean rb=new ResultBean(true,"请求成功");
		String pageIndex = request.getParameter("pageIndex");
		String pagesize = request.getParameter("pagesize");
		String userName = request.getParameter("userName");
		if(StringUtils.isBlank(pageIndex)) {
			pageIndex="0";
		}
		if(StringUtils.isBlank(pagesize)) {
			pagesize="10";
		}
		UserBean bean=new UserBean();
		bean.setUserName(userName);
        QueryResult<UserBean> result = new QueryResult<UserBean>();
		try {
			result = usersService.getUserList(Integer.valueOf(pageIndex),Integer.valueOf(pagesize) , bean);
		} catch (Exception e) {
			rb.setSuccess(false);
			rb.setMessage("系统异常!"+e.getMessage());
			e.printStackTrace();
		}
		rb.setData(result);
        String json = JSON.toJSONString(rb);
		OutputStream out = response.getOutputStream();
		out.write(json.getBytes("UTF-8"));
		out.flush();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
