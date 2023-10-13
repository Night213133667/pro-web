package com.chen.myssm.listeners;

import com.chen.myssm.ioc.BeanFactory;
import com.chen.myssm.ioc.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * ClassName: MyServletContextListener
 * Package: com.chen.listener
 * Description:
 *          监听上下文启动，在上下文启动时创建IOC容器，然后保存到application作用域中
 *          中央控制器再从application作用域中获取IOC容器
 * @Author: Night
 * @Create: 2023/10/2 - 15:28
 * @Version: 1.0
 */

@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //1.获取servletContext对象
        ServletContext application = servletContextEvent.getServletContext();
        //2.获取上下文初始化参数
        String path = application.getInitParameter("contextConfigLocation");
        //3.创建IOC容器
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);
        //4.将IOC容器保存到application作用域
        application.setAttribute("beanFactory",beanFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
