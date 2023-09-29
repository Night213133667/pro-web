package com.chen.fruit.servlets;

import com.chen.fruit.dao.FruitDAO;
import com.chen.fruit.dao.impl.FruitDAOImpl;
import com.chen.fruit.pojo.Fruit;
import com.chen.myssm.myspringmvc.ViewBaseServlet;
import com.chen.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: EditServlet
 * Package: com.chen.fruit.servlets
 * Description:
 *
 * @Author: Night
 * @Create: 2023/9/27 - 11:15
 * @Version: 1.0
 */
@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fidStr = request.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            Fruit fruit = fruitDAO.getFruitById(fid);

            request.setAttribute("fruit",fruit);
            super.processTemplate("edit",request,response);
        }
    }
}
