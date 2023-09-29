package com.chen.fruit.servlets;

import com.chen.fruit.dao.impl.FruitDAOImpl;
import com.chen.fruit.pojo.Fruit;
import com.chen.myssm.myspringmvc.ViewBaseServlet;
import com.chen.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * ClassName: IndexServlet
 * Package: com.chen.fruit.servlets
 * Description:
 *
 * @Author: Night
 * @Create: 2023/9/25 - 19:21
 * @Version: 1.0
 */
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("UTF-8");
        //获取session保存作用域
        HttpSession session = req.getSession();

        FruitDAOImpl fruitDAO = new FruitDAOImpl();
        //设置当前页数为1
        Integer pageNum = 1;


        String keyword = null;
        //获取标签name为operation的参数
        String operation = req.getParameter("operation");
        if (StringUtil.isNotEmpty(operation)){
            if ("search".equals(operation)){
                //模糊查询后，页数为重置为1
                pageNum = 1;
                //获取name为keyword的参数
                keyword = req.getParameter("keyword");
                //判断keyword是否为空null,若果是则设置为空字符串，即SQL模糊查询%%
                if (StringUtil.isEmpty(keyword)){
                    keyword = "";
                }
                //将keyword保存(覆盖)到作用域中
                session.setAttribute("keyword",keyword);
            }
        }else{
            //说明此处为非特殊操作请求之一--分页功能
            //对应page事务函数，从URL地址栏中获取pageNum值
            String pageNumStr = req.getParameter("pageNum");
            if (StringUtil.isNotEmpty(pageNumStr)){
                pageNum = Integer.valueOf(pageNumStr);
            }
            //此时keyword从session作用域获取
            Object keywordObject = session.getAttribute("keyword");
            //如果非操作非search，那么查询基于现有的keyword进行查询
            if (keywordObject != null){
                keyword = (String) keywordObject;
            }else {
                keyword = "";
            }
        }


        //获取库存记录
        List<Fruit> list = fruitDAO.getFruitList(keyword,pageNum);
        //总记录条数
        long fruitCount = fruitDAO.getFruitCount(keyword);
        //总页数
        long pageCount = (fruitCount +5 - 1) / 5;


        //更新当前页面值
        session.setAttribute("pageNum",pageNum);
        //保存数据库返回列表
        session.setAttribute("fruitList",list);
        //保存总页数
        session.setAttribute("pageCount",pageCount);

        //视图
        super.processTemplate("index",req,resp);
    }
}
