package com.chen.qqzone.service;

import com.chen.qqzone.pojo.Reply;
import com.chen.qqzone.pojo.Topic;

import java.util.List;

/**
 * ClassName: ReplyService
 * Package: com.chen.qqzone.service.impl
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/3 - 22:46
 * @Version: 1.0
 */
public interface ReplyService {
    //根据topic的id获取相关回复
    List<Reply> getReplyListByTopicId(Integer topicId);

    //添加回复
    void addReply(Reply reply);

    //删除指定回复
    void delReply(Integer replyId);

    void delReplyList(Topic topic);
}
