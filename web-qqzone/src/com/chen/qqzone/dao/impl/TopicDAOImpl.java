package com.chen.qqzone.dao.impl;



import com.chen.myssm.basedao.BaseDAO;
import com.chen.qqzone.dao.TopicDAO;
import com.chen.qqzone.pojo.Topic;
import com.chen.qqzone.pojo.UserBasic;

import java.util.List;

public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return super.executeQuery("select * from t_topic where author = ? " , userBasic.getId());
    }

    @Override
    public void addTopic(Topic topic) {

    }

    @Override
    public void delTopic(Topic topic) {
        executeUpdate("DELETE FROM t_topic WHERE id = ?",topic.getId());
    }

    @Override
    public Topic getTopic(Integer id) {
        return super.load("SELECT * FROM t_topic WHERE id = ?", id);
    }
}
