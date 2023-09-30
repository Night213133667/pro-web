package com.chen.fruit.controllers;


import com.chen.fruit.dao.FruitDAO;
import com.chen.fruit.dao.impl.FruitDAOImpl;
import com.chen.fruit.pojo.Fruit;
import com.chen.myssm.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * ClassName: FruitServlet
 * Package: com.chen.fruit.servlets
 * Description:
 *
 * @Author: Night
 * @Create: 2023/9/29 - 18:28
 * @Version: 1.0
 */

public class FruitController{
    /*  解决controller层继承ViewBaseServlet时ServletContext未赋值的问题
    之前FruitServlet是一个Servlet组件,那么其中的init方法一定会被调用
    之前的init()方法内部会出现一句话:super.init()


    private ServletContext servlet`Context;

    public void setServletContext(ServletContext servletContext) throws ServletException {
        this.servletContext = servletContext;
        super.init(servletContext);
    }
    */


    private final FruitDAO fruitDAO = new FruitDAOImpl();

    /**
     *
     * @param request 请求
     * @param operate 获取对应功能名称
     * @param keyword 获取查询功能中输入的关键字
     * @param pageNum 对应page事务函数，获取URL地址栏中的pageNum值
     * @return 返回
     */
    private String index(HttpServletRequest request,String operate,String keyword, Integer pageNum){
        //获取session保存作用域
        HttpSession session = request.getSession();
        FruitDAOImpl fruitDAO = new FruitDAOImpl();
        if (pageNum == null){
            pageNum = 1;
        }

        if (StringUtil.isNotEmpty(operate)){
            if ("search".equals(operate)){
                //模糊查询后，页数为重置为1
                pageNum = 1;
                //判断keyword是否为空null,若果是则设置为空字符串，即SQL模糊查询%%
                if (StringUtil.isEmpty(keyword)){
                    keyword = "";
                }
                //将keyword保存(覆盖)到作用域中
                session.setAttribute("keyword",keyword);
            }
        }else{
            //说明此处为非特殊操作请求之一--分页功能
            //从session作用域获取keyword
            Object keywordObject = session.getAttribute("keyword");
            //如果操作非search，那么查询基于现有的keyword进行查询
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
        return "index";
    }

    private String add(String fname, Integer price, Integer fcount, String remark){
        fruitDAO.addFruit(new Fruit(0,fname,price,fcount,remark));
        return "redirect:fruit.do";
    }

    private String del(Integer fid){
        if(fid != null){
            fruitDAO.delFruitById(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String edit(HttpServletRequest request,Integer fid){
        if (fid != null){
            Fruit fruit = fruitDAO.getFruitById(fid);

            request.setAttribute("fruit",fruit);
            //资源跳转
            //super.processTemplate("edit",request,response);
            return "edit";
        }
        return "error";
    }

    private String update(Integer fid, String fname, Integer price, Integer fcount, String remark){
        //3.执行更新
        fruitDAO.updateFruit(new Fruit(fid,fname,price,fcount,remark));
        //4.资源跳转
        //response.sendRedirect("fruit.do");
        return "redirect:fruit.do";
    }
}
