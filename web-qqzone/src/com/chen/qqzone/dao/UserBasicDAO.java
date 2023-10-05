package com.chen.qqzone.dao;

import com.chen.qqzone.pojo.Topic;
import com.chen.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * ClassName: UserBasicDAO
 * Package: com.chen.qqzone.dao
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/2 - 23:23
 * @Version: 1.0
 */
public interface UserBasicDAO {
    //根据账号和密码获取特定用户信息
    UserBasic getUserBasic(String loginId, String pwd);

    //获取指定用户的好友列表
    List<UserBasic> getFriendList(UserBasic userBasic);

    //根据id查询UserBasic的详细信息
    UserBasic getUserBasicById(Integer id);
}
