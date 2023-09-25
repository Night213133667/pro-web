package com.chen.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo07Servlet
 * Package: com.chen.servlets
 * Description:
 *
 * @Author: Night
 * @Create: 2023/9/25 - 17:44
 * @Version: 1.0
 */
public class Demo07Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("demo07...");
    }
}
