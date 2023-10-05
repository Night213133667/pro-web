package com.chen.qqzone.controller;

import com.chen.qqzone.pojo.Topic;
import com.chen.qqzone.pojo.UserBasic;
import com.chen.qqzone.service.TopicService;
import com.chen.qqzone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName: UserController
 * Package: com.chen.qqzone.controller
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/3 - 14:54
 * @Version: 1.0
 */
public class UserController {
    private UserBasicService userBasicService = null;
    private TopicService topicService = null;
    public String login(HttpSession session, String loginId, String pwd){

        UserBasic userBasic = userBasicService.login(loginId, pwd);
        if (userBasic != null){
            //1.获取当前用户的好友列表
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
            //2.获取当前用户的日志信息
            List<Topic> topicList = topicService.getTopicList(userBasic);
            userBasic.setFriendList(friendList);
            userBasic.setTopicList(topicList);

            //userBasic这个key保存的是当前登录用户的信息
            session.setAttribute("userBasic",userBasic);
            //friend这个key保存的是进入的空间是谁的
            session.setAttribute("friend",userBasic);
            return "index";
        }else {
            return "login";
        }
    }

    public String friend(HttpSession session, Integer id){
        UserBasic currentFriend = userBasicService.getUserBasicById(id);
        List<Topic> topicList = topicService.getTopicList(currentFriend);
        currentFriend.setTopicList(topicList);

        session.setAttribute("friend",currentFriend);
        return "index";
    }
}
