package com.chen.qqzone.service;

import com.chen.qqzone.pojo.HostReply;

/**
 * ClassName: HostReplyService
 * Package: com.chen.qqzone.service
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/3 - 23:08
 * @Version: 1.0
 */
public interface HostReplyService {
    HostReply getHostReplyByReplyId(Integer replyId);
    void delHostReply(Integer id);
}
