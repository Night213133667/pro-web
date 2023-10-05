package com.chen.qqzone.dao.impl;

import com.chen.myssm.basedao.BaseDAO;
import com.chen.qqzone.dao.UserBasicDAO;
import com.chen.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * ClassName: UserBasicDAOImpl
 * Package: com.chen.qqzone.dao.impl
 * Description:
 *
 * @Author: Night
 * @Create: 2023/10/2 - 23:32
 * @Version: 1.0
 */
public class UserBasicDAOImpl extends BaseDAO<UserBasic> implements UserBasicDAO {
    @Override
    public UserBasic getUserBasic(String loginId, String pwd) {
        return super.load("SELECT * FROM t_user_basic WHERE loginId = ? AND pwd = ?", loginId, pwd);
    }

    @Override
    public List<UserBasic> getFriendList(UserBasic userBasic) {
        return super.executeQuery("SELECT fid AS id FROM t_friend WHERE uid = ?", userBasic.getId());
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        return super.load("SELECT * FROM t_user_basic WHERE id = ?", id);
    }


}
