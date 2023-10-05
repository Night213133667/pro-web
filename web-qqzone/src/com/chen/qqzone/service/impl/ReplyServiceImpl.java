package com.chen.qqzone.service.impl;

import com.chen.qqzone.dao.ReplyDAO;
import com.chen.qqzone.dao.UserBasicDAO;
import com.chen.qqzone.pojo.HostReply;
import com.chen.qqzone.pojo.Reply;
import com.chen.qqzone.pojo.Topic;
import com.chen.qqzone.pojo.UserBasic;
import com.chen.qqzone.service.HostReplyService;
import com.chen.qqzone.service.ReplyService;
import com.chen.qqzone.service.UserBasicService;

import java.util.List;

/**
 * ClassName: ReplyServiceImpl
 * Package: com.chen.qqzone.service.impl
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/3 - 22:56
 * @Version: 1.0
 */
public class ReplyServiceImpl implements ReplyService {
    private ReplyDAO replyDAO = null;
    //此处引用的是其他POJO层对应的service，而不是DAO
    //其他POJO对应的业务逻辑是封装在Service层的，我需要调用别人的业务逻辑方法，而不是深入考虑人家内部细节
    private HostReplyService hostReplyService = null;
    private UserBasicService userBasicService = null;
    @Override
    public List<Reply> getReplyListByTopicId(Integer topicId) {
        List<Reply> replyList = replyDAO.getReplyList(new Topic(topicId));
        for (int i = 0; i < replyList.size(); i++) {
            Reply reply = replyList.get(i);
            UserBasic replyAuthor = userBasicService.getUserBasicById(reply.getAuthor().getId());
            reply.setAuthor(replyAuthor);
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            reply.setHostReply(hostReply);
        }

        return replyList;
    }

    @Override
    public void addReply(Reply reply) {
        replyDAO.addReply(reply);
    }

    @Override
    public void delReply(Integer replyId) {
        //1.根据id获取到reply
        Reply reply = replyDAO.getReply(replyId);
        if(reply!=null){
            //2.如果reply有关联的hostReply，则先删除hostReply
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            if(hostReply!=null){
                hostReplyService.delHostReply(hostReply.getId());
            }
            //3.删除reply
            replyDAO.delReply(replyId);
        }
    }

    @Override
    public void delReplyList(Topic topic) {
        List<Reply> replyList = replyDAO.getReplyList(topic);
        if (replyList != null){
            for(Reply reply : replyList){
                delReply(reply.getId());
            }
        }
    }


}
