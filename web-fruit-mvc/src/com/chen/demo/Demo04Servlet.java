package com.chen.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ClassName: Demo02Servlet
 * Package: com.chen.demo
 * Description:
 *
 * @Author: Night
 * @Create: 2023/9/27 - 1:16
 * @Version: 1.0
 */
@WebServlet("/demo04")
public class Demo04Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object uname = session.getAttribute("uname");
        System.out.println(uname);
    }
}
