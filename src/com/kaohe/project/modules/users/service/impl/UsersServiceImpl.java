package com.kaohe.project.modules.users.service.impl;

import com.kaohe.project.modules.common.page.QueryResult;
import com.kaohe.project.modules.users.dao.IUsersDao;
import com.kaohe.project.modules.users.dao.bean.UserBean;
import com.kaohe.project.modules.users.dao.impl.UsersDaoImpl;
import com.kaohe.project.modules.users.entity.User;
import com.kaohe.project.modules.users.service.IUsersService;
import com.kaohe.project.sysconfig.utils.EmailUtil;

/**
 * 用户服务层
 * 
 * @author liangrui
 * @date 2021-02-23
 *
 */
public class UsersServiceImpl implements IUsersService {
	IUsersDao userdao = new UsersDaoImpl();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public QueryResult<UserBean> getUserList(int page, int pagesize, UserBean user) throws Exception {
		return userdao.getUserList(page, pagesize, user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserBean get(int userId) throws Exception {
		return userdao.get(userId);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int add(User user, String url, String email) throws Exception {
		System.out.print(url);
		EmailUtil.sendContextEmailHTML(url, "考核系统邮箱注册验证", email, email);
		return userdao.add(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int update(User user) throws Exception {
		return userdao.update(user);
	}

	@Override
	public int update(String userName, int status) throws Exception {
		return userdao.update(userName, status);
	}

	@Override
	public int updateByCode(String code) throws Exception {
		return userdao.updateByCode(code);
	}

	@Override
	public UserBean getUser(String username) throws Exception {
		return userdao.getUser(username);
	}

	@Override
	public UserBean getUser(String username, String password) throws Exception {
		return userdao.getUser(username, password);
	}

	@Override
	public User queryByUserName(String username) {
		return userdao.queryByUserName(username);
	}

	@Override
	public int del(int userId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
