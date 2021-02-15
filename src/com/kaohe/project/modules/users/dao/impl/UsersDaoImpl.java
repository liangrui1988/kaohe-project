package com.kaohe.project.modules.users.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.kaohe.project.modules.common.page.QueryResult;
import com.kaohe.project.modules.users.dao.IUsersDao;
import com.kaohe.project.modules.users.dao.bean.UserBean;
import com.kaohe.project.modules.users.entity.Menu;
import com.kaohe.project.modules.users.entity.User;
import com.kaohe.project.sysconfig.jdbc.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户dao操作
 * 
 * @author liangrui
 * @date 2021-02-09
 *
 */
public class UsersDaoImpl extends BaseDao implements IUsersDao {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 查询用户列表
	 * 
	 * @throws Exception
	 */
	@Override
	public QueryResult<UserBean> getUserList(int page, int pagesize, UserBean user) throws Exception {
		// 组合分页信息
		QueryResult<UserBean> queryResult = new QueryResult<UserBean>();
		Object[] parmObj = { page, pagesize };
		Object[] parmObjCount = {};

		try {
			// 查询列表
			StringBuffer sql = new StringBuffer(512);
			sql.append("select * from sys_user");
			// 取总条数
			StringBuffer sqlCount = new StringBuffer(512);
			sqlCount.append("select count(1) from sys_user");
			// 条件处理
			if (user != null && user.getUserName() != null && !"".equals(user.getUserName())) {
				sql.append(" where user_name like '"+user.getUserName()+"%' ");
				sqlCount.append(" where user_name like '"+user.getUserName()+"%' ");
//				parmObj = new Object[] { page, pagesize };
//				parmObjCount = new Object[] {  };
			}
			// 分页
			sql.append(" order by id  ");
			sql.append(" limit ? , ?  ");
			logger.info("sql>>> \n" + sql.toString());
			ResultSet result = select(sql.toString(), parmObj);
			List<UserBean> userBeans = new ArrayList<UserBean>();
			// DateFormat df = new SimpleDateFormat("YYYY-mm-DD HH:MM:SS");
			if (result != null) {
				while (result.next()) {
					UserBean userbean = new UserBean();
					userbean.setId(result.getInt("id"));
					userbean.setUserName(result.getString("user_name"));
					userbean.setStatus(result.getInt("status"));
					userbean.setEmail(result.getString("email"));
					// String cdate=df.format(result.getDate("create_time"));
					userbean.setCreateTime(result.getTimestamp("create_time"));
					userBeans.add(userbean);
				}
			}
			// 总页数 和 取多少条
			int count = this.getCount(sqlCount.toString(), parmObjCount);
			queryResult.setPages(count, pagesize);
			queryResult.setItems(userBeans);
		} catch (Exception e) {
			logger.error("用户查询数据异常:" + e.getMessage());
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			this.close();
		}
		return queryResult;
	}

	@Override
	public UserBean get(int userId) throws Exception {
		UserBean userbean = new UserBean();
		try {
			Object[] partObj = { userId };
			StringBuffer sql = new StringBuffer(512);
			sql.append("select * from sys_user where id=?");
			ResultSet result = select(sql.toString(), partObj);
			while (result.next()) {
				userbean.setId(result.getInt("id"));
				userbean.setUserName(result.getString("user_name"));
				userbean.setStatus(result.getInt("status"));
				userbean.setEmail(result.getString("email"));
			}
			// 用户拥有的角色
			/*
			 * select t1.user_id,t2.user_name,t1.role_id,t3.name role_name from ( select
			 * user_id,role_id from sys_user_role where user_id=1 ) t1 left join sys_user t2
			 * on t1.user_id=t2.id left join sys_role t3 on t1.role_id=t3.id
			 */

			// 用户拥有的权限
			/*
			 * select a1.user_id,a1.role_id,a2.id,a2.name,a2.href,a2.parent_id from ( select
			 * t1.user_id,t1.role_id,t2.menu_id from ( select user_id,role_id from
			 * sys_user_role where user_id=1 ) t1 left join sys_role_menu t2 on
			 * t1.role_id=t2.role_id ) a1 left join sys_menu a2 on a1.menu_id=a2.id
			 */
			StringBuffer psql = new StringBuffer(512);
			psql.append("select a1.user_id,a1.role_id,a2.id,a2.name,a2.href,a2.parent_id from ( \n");
			psql.append(" select t1.user_id,t1.role_id,t2.menu_id from ( \n");
			psql.append("select user_id,role_id from sys_user_role   \n");
			psql.append("where user_id=? \n");
			psql.append(") t1 \n");
			psql.append("left join sys_role_menu t2 \n");
			psql.append("on t1.role_id=t2.role_id \n");
			psql.append(") a1 left join sys_menu a2 \n");
			psql.append("on a1.menu_id=a2.id");
			logger.info("psql>>> \n" + psql.toString());

			ResultSet pResult = this.select(psql.toString(), partObj);
			List<Menu> menus = new ArrayList<Menu>(); // 拥有的菜单列表d
			while (pResult.next()) {
				Menu menu = new Menu();
				menu.setId(pResult.getString("id"));
				menu.setName(pResult.getString("name"));
				menu.setHref(pResult.getString("href"));
				menus.add(menu);
			}
			userbean.setMenus(menus);
		} catch (Exception e) {
			logger.error("用户查询数据异常", e.getMessage());
			throw new Exception(e);
		} finally {
			this.close();
		}
		return userbean;
	}

	@Override
	public int del(int userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 新增用户
	 */
	@Override
	public int add(User user) throws Exception {
		int result = 0;
		try {
			Object[] partObj = { user.getUserName(), user.getPassword(), user.getEmail(), -1,user.getCode() };
			StringBuffer sql = new StringBuffer(512);
			sql.append("INSERT INTO `sys_user`(`user_name`, `password`,  `email`, `status`,`code`) VALUES (?,?,?,?,?)");
			int id = this.insertGetId(sql.toString(), partObj);
			// 加入角色
			if (id > 0) {
				Object[] rParm = { id, 2 };
				String roleSql = "INSERT INTO `sys_user_role`(`user_id`, `role_id`) VALUES (?,?)";
				int result2 = this.update(roleSql.toString(), rParm);
				result += result2;
			}
		} catch (Exception e) {
			logger.error("用户注册数据异常", e.getMessage());
			throw new Exception(e);
		} finally {
			this.close();
		}
		return result;
	}

	/**
	 * 更改用户
	 */
	@Override
	public int update(User user) throws Exception {
		int result = 0;
		try {
			String password = user.getPassword();
			Object[] partObj = { user.getStatus(), user.getEmail(), user.getId() };
			String sql = "update sys_user set status=? and email=?  where id=?";
			if (password != null && !password.equals("")) {
				partObj = new Object[] { user.getStatus(), user.getPassword(), user.getEmail(), user.getId() };
				sql = "update sys_user set status=? and password=? and email=? where id=?";
			}
			result = this.update(sql.toString(), partObj);
		} catch (Exception e) {
			logger.error("用户更新数据异常", e.getMessage());
			throw new Exception(e);
		} finally {
			this.close();
		}
		return result;
	}
	
	
	/**
	 * 更改用户,根据名字
	 */
	@Override
	public int update(String userName,int status) throws Exception {
		int result = 0;
		try {
			Object[] partObj = { status,userName };
			String sql = "update sys_user set status=?   where user_name=?";
			result = this.update(sql.toString(), partObj);
		} catch (Exception e) {
			logger.error("用户更新数据异常", e.getMessage());
			throw new Exception(e);
		} finally {
			this.close();
		}
		return result;
	}

	/**
	 * 更改用户
	 */
	@Override
	public int updateByCode(String code) throws Exception {
		int result = 0;
		try {
			Object[] partObj = { code };
			String sql = "update sys_user set status=0   where code=?";
			result = this.update(sql.toString(), partObj);
		} catch (Exception e) {
			logger.error("用户更新数据异常", e.getMessage());
			throw new Exception(e);
		} finally {
			this.close();
		}
		return result;
	}
	@Override
	public UserBean getUser(String username) throws Exception {
		UserBean userbean = new UserBean();
		try {
			Object[] partObj = { username };
			StringBuffer sql = new StringBuffer(512);
			sql.append("select * from sys_user where user_name=?");
			ResultSet result = this.select(sql.toString(), partObj);
			while (result.next()) {
				userbean.setId(result.getInt("id"));
				userbean.setUserName(result.getString("user_name"));
				userbean.setEmail(result.getString("email"));
			}
		} catch (Exception e) {
			logger.error("用户查询数据异常", e.getMessage());
			throw new Exception(e);
		} finally {
			this.close();
		}
		return userbean;
	}

	@Override
	public Set<String> getUserRole(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getUserPermissions(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> getUserMenus(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User queryByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserBean getUser(String username, String password) throws Exception {
		UserBean userbean = new UserBean();
		try {
			Object[] partObj = { username, password };
			StringBuffer sql = new StringBuffer(512);
			sql.append("select * from sys_user where user_name=? and password=?");
			ResultSet result = this.select(sql.toString(), partObj);
			while (result.next()) {
				userbean.setId(result.getInt("id"));
				userbean.setUserName(result.getString("user_name"));
				userbean.setEmail(result.getString("email"));
				userbean.setStatus(result.getInt("status"));
			}
		} catch (Exception e) {
			logger.error("用户查询数据异常", e.getMessage());
			throw new Exception(e);
		} finally {
			this.close();
		}
		return userbean;
	}

}
