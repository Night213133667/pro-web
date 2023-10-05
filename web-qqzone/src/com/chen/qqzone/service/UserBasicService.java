package com.chen.qqzone.service;

import com.chen.qqzone.dao.UserBasicDAO;
import com.chen.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * ClassName: UserBasicService
 * Package: com.chen.qqzone.service
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/3 - 14:36
 * @Version: 1.0
 */
public interface UserBasicService {
    UserBasic login(String longinId, String pwd);
    //根据指定id获取其好友列表
    List<UserBasic> getFriendList(UserBasic userBasic);
    //根据指定id获取用户信息
    UserBasic getUserBasicById(Integer id);
}
