package com.chen.qqzone.dao.impl;

import com.chen.myssm.basedao.BaseDAO;
import com.chen.qqzone.dao.HostReplyDAO;
import com.chen.qqzone.pojo.HostReply;

/**
 * ClassName: HostReplyDAOImpl
 * Package: com.chen.qqzone.dao.impl
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/3 - 23:10
 * @Version: 1.0
 */
public class HostReplyDAOImpl extends BaseDAO<HostReply> implements HostReplyDAO {

    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return super.load("SELECT * FROM t_host_reply WHERE reply = ?", replyId);
    }

    @Override
    public void delHostReply(Integer id) {
        super.executeUpdate("delete from t_host_reply where id = ? " , id) ;
    }
}
