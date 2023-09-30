mvc-servlet优化:
    1.将多个Servlet优化为一个模块Servlet的多个方法
    2.将判别调用方法的switch-case优化为使用反射获取方法数组获取特定数组调用
    3.在src目录下建立applicationContext.xml配置文件设置bean让controller与相应id对应
    4.创建DispatcherServlet中央控制器来获取xml配置信息封装成beanMap,之后获取URL信息调用对应的组件
        4.1 从URL中提取servletPath: /fruit.do -> fruit
        4.2 根据fruit找到对应的组件:FruitController,这个对应根据applicationContext.xml中的bean来获取
            DOM技术
        4.3 再根据获取到的特定(operation)来调用controller中对应的功能方法
    5.DispatcherServlet抽取controller层中重复方法，对其进行统一调度管理
      对controller中的方法进行解耦，使其完全成为一个javabean
        5.1 获取参数
            5.1.1 method.getParameters()，获取方法中的所有参数信息
            5.1.2 parameter.getType(), 获取参数类型信息
            5.1.3 Object[] parameterValues, 存放参数值
            5.1.4 考虑参数类型，对其进行类型转换
        5.2 调用方法
            method.invoke(instance, args)
        5.3 视图处理
            对方法返回的字符串解析，然后进行重定向或转发操作

常见错误:
    1.java.lang.IllegalArgumentException: argument type mismatch