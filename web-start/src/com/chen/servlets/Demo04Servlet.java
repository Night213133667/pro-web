package com.chen.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo04Servlet
 * Package: com.chen.servlets
 * Description:
 *      演示HttpSession保存数据
 * @Author: Night
 * @Create: 2023/9/24 - 21:24
 * @Version: 1.0
 */
public class Demo04Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("uname","lina");
    }
}
