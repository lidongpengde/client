package com.boot.filter;

import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by lidongpeng on 2018/2/26.
 */
public class AuthFilter implements Filter{
    final String localSession="localSession";
    final String ssoLoginUrl="http://www.sso.com/toLogin";
    private String artifactParameterName = "ticket";
    private String serverName = "http://www.client.com";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
         HttpSession session = request.getSession(false);
        session = request.getSession();
        final Object assertion = session.getAttribute(localSession);

        String eee=request.getRequestURI();
        String refer=serverName+eee;
        if (assertion != null) {
            filterChain.doFilter(request, response);
            return;
        }
        final String ticket = request.getParameter(artifactParameterName);
        if (!StringUtils.isEmpty(ticket)) {
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("执行了------------authFilter");
        response.sendRedirect(ssoLoginUrl+"?redirect="+refer);
    }

    @Override
    public void destroy() {

    }
}
