package com.kaohe.project.modules.users.service;

import com.kaohe.project.modules.common.page.QueryResult;
import com.kaohe.project.modules.users.dao.bean.UserBean;
import com.kaohe.project.modules.users.entity.User;

/**
 * 用户服务层
 * 
 * @author liangrui
 * @date 2021-02-23
 *
 */
public interface IUsersService {
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

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int add(User user,String url, String email) throws Exception;

	/**
	 * 更改用户
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int update(User user) throws Exception;

	int update(String userName, int status) throws Exception;

	int updateByCode(String code) throws Exception;

	UserBean getUser(String username) throws Exception;

	UserBean getUser(String username, String password) throws Exception;

	User queryByUserName(String username);

	int del(int userId);

}
