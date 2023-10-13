/**
 * ClassName: CookieServlet01
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/8 - 15:58
 * @Version: 1.0
 */
package com.chen.cookies.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/cookie01")
public class CookieServlet01 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.创建Cookie对象
        Cookie cookie = new Cookie("uname","chen");
        //2.将这个对象保存到客户端
        resp.addCookie(cookie);
        //设置有效时长为60s
        cookie.setMaxAge(60);
        //设置 cookie 适用的域
        //cookie.setDomain(pattern);

        req.getRequestDispatcher("hello01.html").forward(req,resp);
    }
}
