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
 * ClassName: UpdateServlet
 * Package: com.chen.fruit.servlets
 * Description:
 *
 * @Author: Night
 * @Create: 2023/9/27 - 15:49
 * @Version: 1.0
 */
@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {
    private FruitDAO fruitDAO = new FruitDAOImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.设置编码
        request.setCharacterEncoding("UTF-8");

        //2.获取参数
        Integer fid = Integer.valueOf((request.getParameter("fid")));
        String fname = request.getParameter("fname");
        Integer price = Integer.valueOf((request.getParameter("price")));
        Integer fcount = Integer.valueOf((request.getParameter("fcount")));
        String remark = request.getParameter("remark");

        //3.执行更新
        fruitDAO.updateFruit(new Fruit(fid,fname,price,fcount,remark));

        //4.资源跳转
        //super.processTemplate("index",request,response);
        //此处需要重定向，目的是重新给indexServlet发送请求，重新获取fruitList，然后覆盖到session中，这样index.html页面上显示的内容才是最新的
        response.sendRedirect("index");
    }
}
