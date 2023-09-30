package com.chen.demo;

import javax.servlet.ServletContext;
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
@WebServlet("/demo05")
public class Demo05Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //向application保存作用域保存数据
        ServletContext application = request.getServletContext();
        application.setAttribute("uname","lili");
        //2.客户端重定向
        //response.sendRedirect("demo06");
        //3.服务器转发
        request.getRequestDispatcher("demo06").forward(request,response);
    }
}
