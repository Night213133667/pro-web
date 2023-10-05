package com.chen.qqzone.controller;


import com.chen.qqzone.pojo.Topic;
import com.chen.qqzone.pojo.UserBasic;
import com.chen.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * ClassName: TopicController
 * Package: com.chen.qqzone.controller
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/3 - 22:35
 * @Version: 1.0
 */
public class TopicController {
    private TopicService topicService = null;
    public String topicDetail(HttpSession session, Integer id){
        //查询特定id的用户的日志
        Topic topic = topicService.getTopicById(id);

        session.setAttribute("topic",topic);

        return "frames/detail";
    }

    public String delTopic(Integer topicId){
        topicService.delTopic(topicId);
        return "redirect:topic.do?operation=getTopicList";
    }

    public String getTopicList(HttpSession session){
        //从session中获取当前用户信息
        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");
        //查询当前用户的所有日志
        List<Topic> topicList = topicService.getTopicList(userBasic);
        //设置一下关联日志列表
        userBasic.setTopicList(topicList);
        //重新覆盖friend中的信息，main.html迭代的是friend中的数据
        session.setAttribute("friend",userBasic);
        return "frames/main";
    }

    //todo:发表新日志,参照添加主人回复
}
