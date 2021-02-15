package com.kaohe.project.modules.users.dao;

import java.util.List;
import java.util.Set;

import com.kaohe.project.modules.common.page.QueryResult;
import com.kaohe.project.modules.users.dao.bean.UserBean;
import com.kaohe.project.modules.users.entity.Menu;
import com.kaohe.project.modules.users.entity.User;

/**
 * 用户dao操作
 * 
 * @author liangrui
 * @date 2021-02-09
 *
 */
public interface IUsersDao {

	/**
	 * 查询用户
	 * 
	 * @param page
	 * @param pagesize
	 * @param user
	 * @return
	 * @throws Exception
	 */
	QueryResult<UserBean> getUserList(int page, int pagesize, UserBean user) throws Exception;

	/**
	 * 获取单个用户
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	UserBean get(int userId) throws Exception;

	int del(int userId);

	int add(User user) throws Exception;

	/**
	 * 更改用户
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int update(User user) throws Exception;

	int update(String userName,int status) throws Exception;
	
	UserBean getUser(String username) throws Exception;
	
	UserBean getUser(String username,String password) throws Exception;


	Set<String> getUserRole(String username);

	Set<String> getUserPermissions(String username);

	List<Menu> getUserMenus(String username);

	User queryByUserName(String username);

}
