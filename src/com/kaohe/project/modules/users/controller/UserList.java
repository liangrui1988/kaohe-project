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
 * Servlet implementation class UserList
 */
@WebServlet("/UserList")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUsersDao userdao = new UsersDaoImpl();

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
		
		if(pageIndex==null||"".equals(pageIndex)) {
			pageIndex="0";
		}
		if(pagesize==null||"".equals(pagesize)) {
			pagesize="20";
		}
        
        QueryResult<UserBean> result = new QueryResult<UserBean>();
		try {
			result = userdao.getUserList(Integer.valueOf(pageIndex),Integer.valueOf(pagesize) , new UserBean());
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
