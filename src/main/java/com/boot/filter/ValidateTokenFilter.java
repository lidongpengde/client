package com.boot.filter;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by lidongpeng on 2018/2/26.
 */
public class ValidateTokenFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response1 = (HttpServletResponse) servletResponse;
        String ticket=request.getParameter("ticket");
        if (ticket==null){
            filterChain.doFilter(request, response1);
            return;
        }
        String result=postticket(ticket);
        if (StringUtils.isNotBlank(result)){
            request.getSession().setAttribute("localSession",result);
            filterChain.doFilter(request, response1);
            return;
        }
    }

    @Override
    public void destroy() {

    }
    public  String postticket(String ticket) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建一个post对象

        HttpPost post =new HttpPost("http://localhost:8082/tickets/"+ticket);

        //执行post请求

        CloseableHttpResponse response =httpClient.execute(post);

        String result = EntityUtils.toString(response.getEntity());

        System.out.println(result);

        response.close();

        httpClient.close();
        return result;
    }
}
