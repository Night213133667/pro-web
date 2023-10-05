package com.chen.myssm.basedao;

/**
 * ClassName: DAOException
 * Package: com.chen.myssm.basedao
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/2 - 14:27
 * @Version: 1.0
 */
public class DAOException extends RuntimeException{
        public DAOException(String msg){
        super(msg);
    }
}