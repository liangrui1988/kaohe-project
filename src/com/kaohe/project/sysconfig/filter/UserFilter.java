package com.kaohe.project.sysconfig.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;



		
		HttpSession session = request.getSession();
		String currPath = request.getRequestURI(); // 当前请求的URL
		System.out.println("UserFilter >> currPath:"+ currPath);

		filterChain.doFilter(servletRequest, servletResponse);
		/*
		 * if (session.getAttribute("logined") != null) {
		 * filterChain.doFilter(servletRequest, servletResponse); } else {
		 * response.sendRedirect("/modules/users/permissionDenied.jsp"); }
		 */
	}

	@Override
	public void destroy() {

	}
}