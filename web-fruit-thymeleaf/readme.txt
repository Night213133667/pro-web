review:
1.设置编码，防止中文乱码
    post: request.setCharacterEncoding("UTF-8");
    get:  String name = request.getParameter("name");
          byte[] bytes = name.getBytes("iso-8859-1");
          name = new String(bytes,"UTF-8");
    get在Tomcat8之后无需再设置编码

2.Servlet继承关系以及生命周期
    1) Servlet接口 : init()、service()、destroy()
       对应生命周期阶段: 初始化阶段、服务阶段、销毁阶段


    2) 生命周期: 实例化、初始化、服务、销毁
        -Servlet启动时机:<load-on-startup>
        -Servlet3.0开始支持注解注册

3.HTTP协议
    1)由request和response组成
    2)请求三部分: 请求行、请求消息头、请求体 : get:query string; post:form data; json:request payload
    3)响应三部分:

4.HTTPSession:
    为什么要使用会话:HTTP是无状态的，不能判断多次请求是否为同一客户端发送
    Session保存作用域

5.服务器内部转发和客户端重定向

6.thymeleaf部分标签

7.四个保存作用域(page,request,session,application)

8.路径问题(建议使用绝对路径)
    8.1 base设置根路径
    8.2 thymeleaf中@{}动态获取根路径信息

9.实现库存系统
