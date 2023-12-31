package com.chen.myssm.myspringmvc;

import com.chen.myssm.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

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

    private Map<String,Object> beanMap = new HashMap<>();

    public DispatcherServlet(){

    }

    public void init() throws ServletException {
        super.init();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            //1.创建documentBuilderFactory对象
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            //2.创建DocumentBuilder对象
            DocumentBuilder documentBuilder = docFactory.newDocumentBuilder();
            //3.创建Document对象
            Document document = documentBuilder.parse(inputStream);

            //4.获取所有的bean结点
            NodeList beanList = document.getElementsByTagName("bean");
            for (int i = 0; i < beanList.getLength(); i++) {
                Node beanNode = beanList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    String beanClassName = beanElement.getAttribute("class");
                    Object beanObj = Class.forName(beanClassName).newInstance();
                    //5.将bean放入map中
                    beanMap.put(beanId,beanObj);
                }

            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        //获取Servlet名称
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1);
        int lastDotIndex = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0,lastDotIndex);
        //在hashmap中获取对应的controller类
        Object controllerBeanObj = beanMap.get(servletPath);

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
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }
}
