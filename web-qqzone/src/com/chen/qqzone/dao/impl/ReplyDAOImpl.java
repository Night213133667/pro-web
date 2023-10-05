package com.chen.qqzone.dao.impl;

import com.chen.myssm.basedao.BaseDAO;
import com.chen.qqzone.dao.ReplyDAO;
import com.chen.qqzone.pojo.Reply;
import com.chen.qqzone.pojo.Topic;

import java.util.List;

/**
 * ClassName: ReplyDAO
 * Package: com.chen.qqzone.dao.impl
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/3 - 23:00
 * @Version: 1.0
 */
public class ReplyDAOImpl extends BaseDAO<Reply> implements ReplyDAO {
    @Override
    public List<Reply> getReplyList(Topic topic) {
        return super.executeQuery("SELECT * FROM t_reply WHERE topic = ?",topic.getId());
    }

    @Override
    public void addReply(Reply reply) {
        super.executeUpdate("INSERT INTO t_reply values(0, ?, ?, ?, ?)",reply.getContent(),reply.getReplyDate(),reply.getAuthor().getId(),reply.getTopic().getId());
    }

    @Override
    public void delReply(Integer id) {
        super.executeUpdate("DELETE FROM t_reply WHERE id = ?",id);
    }

    @Override
    public Reply getReply(Integer id) {
        return load("select * from t_reply where id =? " , id);
    }
}
