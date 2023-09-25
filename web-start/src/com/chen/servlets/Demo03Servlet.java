package com.chen.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ClassName: Demo03Servlet
 * Package: com.chen.servlets
 * Description:
 *      演示Session
 * @Author: Night
 * @Create: 2023/9/24 - 20:15
 * @Version: 1.0
 */
public class Demo03Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Session,如果获取不到，则创建一个新的
        HttpSession session = req.getSession();
        System.out.println("SessionID:" + session.getId());
        session.getMaxInactiveInterval();
        session.getLastAccessedTime();

        session.invalidate();
    }
}
