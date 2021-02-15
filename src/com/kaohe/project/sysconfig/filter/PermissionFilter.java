package com.kaohe.project.sysconfig.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaohe.project.modules.users.dao.bean.UserBean;
import com.kaohe.project.modules.users.entity.Menu;

/**
 * Servlet Filter implementation class PermissionFilter
 */
@WebFilter("/*")
public class PermissionFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public PermissionFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpSevletRequest = (HttpServletRequest) request;
		HttpServletResponse httpSevletResponse = (HttpServletResponse) response;
		HttpSession session = httpSevletRequest.getSession();
		String currPath = httpSevletRequest.getRequestURI(); // 当前请求的URL
		String contextPath = httpSevletRequest.getContextPath();
		String puri = currPath.replace(contextPath, "");
		// 获取用户角色和权限信息
		UserBean userinfo = (UserBean) session.getAttribute("userinfo");
		List<String> menusLists = new ArrayList<String>();
		if (userinfo != null && userinfo.getUserName() != null && !"".equals(userinfo.getUserName())) {
			//登录过就直接跳到主页
			if (puri.equals("/ShowUserLongin")) {
				httpSevletRequest.setAttribute("tips", "你已登录:" + userinfo.getUserName());
				httpSevletRequest.getRequestDispatcher("/WEB-INF/index.jsp").forward(httpSevletRequest,
						response);
				return;
			}
			List<Menu> menus = userinfo.getMenus(); // 用户的权限列表
			if (menus != null) {
				menus.forEach(r -> {
					menusLists.add("/" + r.getHref());
				});
			}
		}
		// 获取系统权限，这里先写死，可优化从权限表中读取，
		// 拿当前用户拥有的权限，和系统的权限进行对比，进行放行
		if (currPath.equals("/ShowIndex")) { // 首页需要角色为1,或2的角色才能 访问权限，用户注册默认角色为2
			if (!menusLists.contains(puri)) {
				httpSevletRequest.setAttribute("tips", "需要登录才能访问主页");
				httpSevletRequest.getRequestDispatcher("/WEB-INF/modules/users/login.jsp").forward(httpSevletRequest,
						response);
				return;
			}
		} else if (currPath.equals(contextPath + "/ShowManager") || currPath.equals(contextPath + "/ShowUpdateUser")
				|| currPath.equals(contextPath + "/UpdateUser")) {
			// 管理需要角色为1的权限
			if (!menusLists.contains(puri)) {
				httpSevletRequest.setAttribute("tips", "没有权限访问");
				httpSevletRequest.getRequestDispatcher("/permissionDenied.jsp").forward(httpSevletRequest, response);
				return;
			}
		}
		chain.doFilter(httpSevletRequest, httpSevletResponse);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
