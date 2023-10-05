package com.chen.qqzone.dao;

import com.chen.qqzone.pojo.Topic;
import com.chen.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * ClassName: TopicDAO
 * Package: com.chen.qqzone.dao
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/2 - 23:27
 * @Version: 1.0
 */
public interface TopicDAO {
    //获取指定用户的日志列表
    List<Topic> getTopicList(UserBasic userBasic);

    //添加日志
    void addTopic(Topic topic);

    //删除日志
    void delTopic(Topic topic);

    //获取特定日志
    Topic getTopic(Integer id);


}
