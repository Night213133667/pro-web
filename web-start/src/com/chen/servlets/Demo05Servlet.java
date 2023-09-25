package com.chen.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo05Servlet
 * Package: com.chen.servlets
 * Description:
 *      演示从HTTPServlet保存作用域中获取数据
 * @Author: Night
 * @Create: 2023/9/24 - 21:25
 * @Version: 1.0
 */
public class Demo05Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object uname = req.getSession().getAttribute("uname");
        System.out.println(uname);

    }
}
