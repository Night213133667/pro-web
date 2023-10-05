package com.chen.myssm.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.chen.myssm.basedao.DAOException;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
//    public static String DRIVER;
//    public static String URL;
//    public static String USER;
//    public static String PWD;
    public static Properties properties;
    static {
        InputStream is = ConnUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        properties = new Properties();
        try {
            properties.load(is);
//            DRIVER = properties.getProperty("jdbc.driver");
//            URL = properties.getProperty("jdbc.url");
//            USER = properties.getProperty("jdbc.user");
//            PWD = properties.getProperty("jdbc.pwd");
        } catch (IOException e) {
            e.printStackTrace();
            throw new DAOException("数据库配置信息出错");
        }
    }
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    private static Connection createConnection(){
        try {
            DataSource source = DruidDataSourceFactory.createDataSource(properties);
//            source.setMaxWait(5000);
//            source.setMinIdle(3);
//            source.setMaxActive(10);

            return source.getConnection();
        } catch (Exception e) {
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
