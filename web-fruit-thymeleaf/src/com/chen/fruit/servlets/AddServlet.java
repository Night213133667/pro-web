package com.chen.fruit.servlets;

import com.chen.fruit.dao.FruitDAO;
import com.chen.fruit.dao.impl.FruitDAOImpl;
import com.chen.fruit.pojo.Fruit;
import com.chen.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: AddServlet
 * Package: com.chen.fruit.servlets
 * Description:
 *
 * @Author: Night
 * @Create: 2023/9/28 - 19:45
 * @Version: 1.0
 */
@WebServlet("/add.do")
public class AddServlet extends ViewBaseServlet {
    private FruitDAO fruitDAO = new FruitDAOImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String fname = request.getParameter("fname");
        Integer price = Integer.valueOf(request.getParameter("price"));
        Integer fcount = Integer.valueOf(request.getParameter("fcount"));
        String remark = request.getParameter("remark");
        fruitDAO.addFruit(new Fruit(0,fname,price,fcount,remark));

        response.sendRedirect("index");
    }
}
