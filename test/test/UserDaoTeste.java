package test;

import com.kaohe.project.modules.common.page.QueryResult;
import com.kaohe.project.modules.users.dao.bean.UserBean;
import com.kaohe.project.modules.users.dao.impl.UsersDaoImpl;
import com.kaohe.project.modules.users.entity.User;

public class UserDaoTeste {
	public static void main(String[] args) throws Exception {
		UsersDaoImpl userdao = new UsersDaoImpl();
		// 根据名字查询
		// UserBean userbean = userdao.getUser("admin");
		// System.out.println(userbean);
		// 获取用户id
		// UserBean userbean = userdao.get(1);
		// System.out.println(userbean);
		// 更改用户
		// User user = new User();
		// user.setId(1);
		// user.setStatus(1);
		// int result = userdao.update(user);
		// System.out.println(result);

		// 注册用户
		// User useradd = new User();
		// useradd.setUserName("test1");
		// useradd.setPassword("pass");
		// useradd.setEmail("ruiliang@smartbi.com");
		// //useradd.setStatus(1);
		// int result2 = userdao.add(useradd);
		// System.out.println(result2);

		// 分页查询
		UserBean parm = new UserBean();
		QueryResult<UserBean> queryResult = userdao.getUserList(0, 20, parm);
		System.out.println(queryResult.getCount());
		System.out.println(queryResult.getItems());
	}
}
