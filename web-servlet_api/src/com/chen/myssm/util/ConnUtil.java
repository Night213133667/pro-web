package com.chen.myssm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ClassName: ConnUtil
 * Package: com.chen.myssm.basedao
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/2 - 11:28
 * @Version: 1.0
 */
public class ConnUtil {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver" ;
    public static final String URL = "jdbc:mysql://localhost:3306/fruitdb?rewriteBatchedStatements=true&serverTimezone=Asia/Shanghai&characterEncoding=UTF-8";
    public static final String USER = "root";
    public static final String PWD = "chenlong" ;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static Connection createConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getConn(){
        Connection conn = threadLocal.get();
        if (conn == null){
            conn = createConnection();
            threadLocal.set(conn);
        }
        return threadLocal.get();
    }

    public static void closeConn() throws SQLException {
        Connection conn = threadLocal.get();
        if (conn == null){
            return;
        }
        if (!conn.isClosed()){
            conn.close();
            threadLocal.remove();
        }
    }
}
