package com.chen.myssm.myspringmvc;

import com.chen.myssm.ioc.BeanFactory;
import com.chen.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * ClassName: DispacherServlet
 * Package: com.chen.myssm.myspringmvc
 * Description:
 *
 * @Author: Night
 * @Create: 2023/9/30 - 1:16
 * @Version: 1.0
 */
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet{
    private BeanFactory beanFactory;
    public DispatcherServlet(){

    }

    public void init() throws ServletException {
        super.init();
        //beanFactory = new ClassPathXmlApplicationContext();
        //之前是在此处主动创建IOC容器，现在优化为从application作用域中获取
        Object beanFactoryObj = getServletContext().getAttribute("beanFactory");
        if (beanFactoryObj != null){
            beanFactory = (BeanFactory) beanFactoryObj;
        }else {
            throw new RuntimeException("IOC容器获取失败");
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取Servlet名称
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1);
        int lastDotIndex = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0,lastDotIndex);
        //在hashmap中获取对应的controller类
        Object controllerBeanObj = beanFactory.getBean(servletPath);

        String operation = request.getParameter("operation");
        if (StringUtil.isEmpty(operation)){
            operation = "index";
        }


        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for(Method method : methods){
                if (operation.equals(method.getName())){
                    //1.获取参数数组
                    Parameter[] parameters = method.getParameters();
                    //实际参数命名是以arg0,arg1..来命名的,修改JVM编译可获取真实名称
                    //用来承载方法参数的值
                    Object[] parameterValues = new Object[parameters.length];
                    //将参数数组对应的值填入其中
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();
                        //针对特殊参数值进行判断填入
                        if ("request".equals(parameterName)){
                            parameterValues[i] = request;
                        }else if ("response".equals(parameterName)){
                            parameterValues[i] = response;
                        }else if ("session".equals(parameterName)){
                            parameterValues[i] = request.getSession();
                        }else{
                            String parameterValue = request.getParameter(parameterName);
                            String typeName = parameter.getType().getName();
                            //存入数组
                            if (parameterValue != null){
                                if ("java.lang.Integer".equals(typeName)){
                                    parameterValues[i] = Integer.valueOf(parameterValue);
                                }else {
                                    parameterValues[i] = parameterValue;
                                }
                            }
                        }
                    }
                    
                    //2.调用对应controller组件方法
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBeanObj, parameterValues);

                    //3.视图处理
                    String methodReturnValue = (String) returnObj;
                    if (methodReturnValue.startsWith("redirect:")){
                        String redirectUrl = methodReturnValue.substring("redirect:".length());
                        response.sendRedirect(redirectUrl);
                    }else {
                        super.processTemplate(methodReturnValue,request,response);
                    }
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new DispatcherServletException("DispatcherServlet出现异常...");
        }

    }
}
