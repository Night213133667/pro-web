package com.chen.servlets;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: test
 * Package: com.chen.servlets
 * Description:
 *
 * @Author: Night
 * @Create: 2023/9/23 - 17:21
 * @Version: 1.0
 */
public class test extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("正在初始化.....");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("正在服务.....");
    }

    @Override
    public void destroy() {
        System.out.println("正在销毁.....");
    }
}
