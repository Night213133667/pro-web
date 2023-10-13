package com.chen.cookies.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ClassName: KaptchaServletDemo01
 * Package: com.chen.cookies.servlet
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/8 - 17:11
 * @Version: 1.0
 */
@WebServlet("/kaptcha01")
public class KaptchaServletDemo01 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object key = session.getAttribute("KAPTCHA_SESSION_KEY");
        System.out.println(key);
    }
}
