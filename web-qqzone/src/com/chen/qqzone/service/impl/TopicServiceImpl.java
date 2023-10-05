package com.chen.qqzone.service.impl;


import com.chen.qqzone.dao.TopicDAO;
import com.chen.qqzone.pojo.Reply;
import com.chen.qqzone.pojo.Topic;
import com.chen.qqzone.pojo.UserBasic;
import com.chen.qqzone.service.ReplyService;
import com.chen.qqzone.service.TopicService;
import com.chen.qqzone.service.UserBasicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {
    private TopicDAO topicDAO = null ;
    private ReplyService replyService = null;
    private UserBasicService userBasicService = null;
    //获取日志列表
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }

    @Override
    public Topic getTopicById(Integer id) {
        Topic topic = topicDAO.getTopic(id);
        //获取topic的author的详细信息
        UserBasic author = userBasicService.getUserBasicById(topic.getAuthor().getId());
        topic.setAuthor(author);
        //获取topic的reply的详细信息
        List<Reply> replyList = replyService.getReplyListByTopicId(id);
        topic.setReplyList(replyList);

        return topic;
    }

    @Override
    public void delTopic(Integer topicId) {
        Topic topic = topicDAO.getTopic(topicId);
        if (topic != null){
            replyService.delReplyList(topic);

            topicDAO.delTopic(topic);
        }
    }


}
