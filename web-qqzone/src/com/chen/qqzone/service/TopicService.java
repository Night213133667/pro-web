package com.chen.qqzone.service;



import com.chen.qqzone.pojo.Topic;
import com.chen.qqzone.pojo.UserBasic;

import java.util.List;

public interface TopicService {
    //查询特定用户的日志列表
    List<Topic> getTopicList(UserBasic userBasic) ;

    //根据获取特定topic
    Topic getTopicById(Integer id);

    void delTopic(Integer topicId);
}
