package com.chen.demo;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo01Servlet
 * Package: com.chen.servlet
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/1 - 15:15
 * @Version: 1.0
 */
//@WebServlet(urlPatterns = {"/demo01"},
//            initParams = {
//                @WebInitParam(name = "hello",value = "world"),
//                @WebInitParam(name = "uname",value = "jim")
//            }
//            )
public class Demo01Servlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        ServletConfig config = getServletConfig();
        String initValue = config.getInitParameter("hello");
        System.out.println(initValue);
        ServletContext servletContext = config.getServletContext();
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        System.out.println(contextConfigLocation);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext();
        req.getSession().getServletContext();
    }
}
