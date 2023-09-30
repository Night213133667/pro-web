package com.chen.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ClassName: Demo01Servlet
 * Package: com.chen.demo
 * Description:
 *
 * @Author: Night
 * @Create: 2023/9/27 - 1:08
 * @Version: 1.0
 */
@WebServlet("/demo03")
public class Demo03Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //向session保存作用域保存数据
        HttpSession session = request.getSession();
        session.setAttribute("uname","lili");
        //2.客户端重定向,二次请求依旧可以获取第一次request请求中session保存作用域中的数据
        //response.sendRedirect("demo04");
        //3.服务器转发，一次响应请求的过程
        request.getRequestDispatcher("demo04").forward(request,response);
    }
}
