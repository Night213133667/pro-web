package com.chen.qqzone.dao;

import com.chen.qqzone.pojo.HostReply;

/**
 * ClassName: HostReplyDAO
 * Package: com.chen.qqzone.dao
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/2 - 23:32
 * @Version: 1.0
 */
public interface HostReplyDAO {
    //根据replyId查询相关联的HostReply实体
    HostReply getHostReplyByReplyId(Integer replyId);
    void delHostReply(Integer id);
}
