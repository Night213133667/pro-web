package com.chen.fruit.servlets;

import com.chen.fruit.dao.FruitDAO;
import com.chen.fruit.dao.impl.FruitDAOImpl;
import com.chen.myssm.myspringmvc.ViewBaseServlet;
import com.chen.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: DelServlet
 * Package: com.chen.fruit.servlets
 * Description:
 *
 * @Author: Night
 * @Create: 2023/9/28 - 16:48
 * @Version: 1.0
 */
@WebServlet("/del.do")
public class DelServlet extends ViewBaseServlet {
    private FruitDAO fruitDAO = new FruitDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fidStr = request.getParameter("fid");
        if(StringUtil.isNotEmpty(fidStr)){
            Integer fid = Integer.valueOf(fidStr);
            fruitDAO.delFruitById(fid);

            response.sendRedirect("index");
        }

    }
}
