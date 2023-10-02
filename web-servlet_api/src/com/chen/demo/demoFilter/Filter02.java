package com.chen.demo.demoFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * ClassName: Demo01Filter
 * Package: com.chen.filters
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/1 - 22:59
 * @Version: 1.0
 */
//@WebFilter("*.do")
public class Filter02 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //请求前执行
        System.out.println("B");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
        //响应前执行
        System.out.println("B2");
    }

    @Override
    public void destroy() {

    }
}
