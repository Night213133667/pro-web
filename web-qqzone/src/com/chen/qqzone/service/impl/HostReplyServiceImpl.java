package com.chen.qqzone.service.impl;

import com.chen.qqzone.dao.HostReplyDAO;
import com.chen.qqzone.pojo.HostReply;
import com.chen.qqzone.service.HostReplyService;

/**
 * ClassName: HostReplyServiceImpl
 * Package: com.chen.qqzone.service.impl
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/3 - 23:09
 * @Version: 1.0
 */
public class HostReplyServiceImpl implements HostReplyService {
    private HostReplyDAO hostReplyDAO = null;
    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return hostReplyDAO.getHostReplyByReplyId(replyId);
    }

    @Override
    public void delHostReply(Integer id) {
        hostReplyDAO.delHostReply(id);
    }


}
