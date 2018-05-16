package com.ymei_inc.ymadvert.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MD5Filter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO 可进行签名验证
		System.out.println("Filter is running !!!");
		
		chain.doFilter(request, response);// 放行。让其走到下个链或目标资源中
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
