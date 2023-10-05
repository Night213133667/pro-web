package com.chen.qqzone.controller;

import com.chen.qqzone.pojo.Reply;
import com.chen.qqzone.pojo.Topic;
import com.chen.qqzone.pojo.UserBasic;
import com.chen.qqzone.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.sql.Date;

/**
 * ClassName: ReplyController
 * Package: com.chen.qqzone.controller
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/4 - 15:42
 * @Version: 1.0
 */
public class ReplyController {
    private ReplyService replyService = null;
    public String addReply(HttpSession session, Integer topicId , String content){
        UserBasic author = (UserBasic)session.getAttribute("userBasic");
        Reply reply = new Reply(content, new Date(new java.util.Date().getTime()), author, new Topic(topicId));
        replyService.addReply(reply);
        return "redirect:topic.do?operation=topicDetail&id="+topicId;
    }

    public String delReply(Integer replyId , Integer topicId){
        replyService.delReply(replyId);
        return "redirect:topic.do?operate=topicDetail&id="+topicId;
    }
}
