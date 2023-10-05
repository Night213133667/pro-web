package com.chen.qqzone.service.impl;

import com.chen.qqzone.dao.UserBasicDAO;
import com.chen.qqzone.pojo.UserBasic;
import com.chen.qqzone.service.UserBasicService;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: UserBasicServiceImpl
 * Package: com.chen.qqzone.service.impl
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/3 - 14:37
 * @Version: 1.0
 */
public class UserBasicServiceImpl implements UserBasicService {
    private UserBasicDAO userBasicDAO = null;

    @Override
    public UserBasic login(String longinId, String pwd) {
        //1.登录验证

        UserBasic userBasic = userBasicDAO.getUserBasic(longinId, pwd);
        return userBasic;
    }

    @Override
    public List<UserBasic> getFriendList(UserBasic userBasic) {
        List<UserBasic> basicList = userBasicDAO.getFriendList(userBasic);
        List<UserBasic> friendList = new ArrayList<>(basicList.size());
        for (int i = 0; i < basicList.size(); i++) {
            UserBasic friend = basicList.get(i);
            friend = userBasicDAO.getUserBasicById(friend.getId());
            friendList.add(friend);
        }
        return friendList;
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        return userBasicDAO.getUserBasicById(id);
    }

}
