package com.chen.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo06Servlet
 * Package: com.chen.servlets
 * Description:
 *      演示服务器内部转发以及客户端重定向
 * @Author: Night
 * @Create: 2023/9/25 - 17:44
 * @Version: 1.0
 */
public class Demo06Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("demo06...");
        //服务器内部转发
        //request.getRequestDispatcher("demo07").forward(request,response);
        //客户端重定向
        response.sendRedirect("demo07");
    }
}
