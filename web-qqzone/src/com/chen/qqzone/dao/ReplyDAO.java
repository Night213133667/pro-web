package com.chen.qqzone.dao;

import com.chen.qqzone.pojo.Reply;
import com.chen.qqzone.pojo.Topic;

import java.util.List;

/**
 * ClassName: ReplyDAO
 * Package: com.chen.qqzone.dao
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/2 - 23:30
 * @Version: 1.0
 */
public interface ReplyDAO {
    //获取指定日志的回复列表
    List<Reply> getReplyList(Topic topic);

    //添加回复
    void addReply(Reply reply);

    //删除回复
    void delReply(Integer id);

    //根据id获取指定的Reply
    Reply getReply(Integer id);
}
