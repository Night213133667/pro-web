package com.chen.myssm.io;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: ClassPathXmlApplicationContext
 * Package: com.chen.myssm.io
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/1 - 17:57
 * @Version: 1.0
 */
public class ClassPathXmlApplicationContext implements BeanFactory{

    private Map<String,Object> beanMap = new HashMap<>();

    public ClassPathXmlApplicationContext(){
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
                    //创建bean实例
                    Object beanObj = Class.forName(beanClassName).newInstance();
                    //5.将bean保存到map容器中
                    beanMap.put(beanId,beanObj);
                }
            }
            //5.组装bean与bean之间的依赖关系
            for (int i = 0; i < beanList.getLength(); i++) {
                Node beanNode = beanList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    NodeList beanChildNodes = beanElement.getChildNodes();
                    for (int j = 0; j < beanChildNodes.getLength(); j++) {
                        Node beanChildNode = beanChildNodes.item(j);
                        if (beanChildNode.getNodeType() == Node.ELEMENT_NODE && "property".equals(beanChildNode.getNodeName())){
                            Element propertyElement = (Element) beanChildNode;
                            String propertyName = propertyElement.getAttribute("name");
                            String propertyRef = propertyElement.getAttribute("ref");
                            //1.找到property的ref对应的bean实例
                            Object refObjet = beanMap.get(propertyRef);
                            //2.将实例类放到当前bean实例类的property属性上
                            Object beanObj = beanMap.get(beanId);
                            Field propertyField = beanObj.getClass().getDeclaredField(propertyName);
                            propertyField.setAccessible(true);
                            propertyField.set(beanObj,refObjet);
                        }
                    }

                }
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
