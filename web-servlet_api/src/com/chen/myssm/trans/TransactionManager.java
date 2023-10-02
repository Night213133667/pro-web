package com.chen.myssm.trans;

import com.chen.myssm.util.ConnUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ClassName: TransationManager
 * Package: com.chen.myssm.trans
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/2 - 11:19
 * @Version: 1.0
 */
public class TransactionManager {

    //开启事务
    public static void beginTrans() throws SQLException {
        ConnUtil.getConn().setAutoCommit(false);
    }


    //提交事务
    public static void commit() throws SQLException {
        Connection conn = ConnUtil.getConn();
        conn.commit();
        ConnUtil.closeConn();
    }

    //回滚事务
    public static void rollback() throws SQLException {
        ConnUtil.getConn().rollback();
        ConnUtil.closeConn();
    }
}
