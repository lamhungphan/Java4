package com.fpoly.java4_youtube.dao.impl;

import com.fpoly.java4_youtube.constant.NamedStored;
import com.fpoly.java4_youtube.dao.AbstractDao;
import com.fpoly.java4_youtube.dao.UserDao;
import com.fpoly.java4_youtube.entity.User;

import java.util.List;
import java.util.Map;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    @Override
    public User findById(Integer id) {
        return super.findById(User.class, id);
    }

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT o FROM User o WHERE o.email = ?1";
        return super.findOne(User.class, sql, email);
    }

    @Override
    public User findByUserName(String username) {
        String sql = "SELECT o FROM User o WHERE o.username = ?1";
        return super.findOne(User.class, sql, username);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT o FROM User o WHERE o.username = ?1 AND o.password = ?2";
        return super.findOne(User.class, sql, username, password);
    }

    @Override
    public List<User> findAll() {
        return super.findAll(User.class, true);
    }

    @Override
    public List<User> findAll(int pageNumber, int pageSize) {
        return super.findAll(User.class, true, pageNumber, pageSize);
    }

    @Override
    public List<User> findUsersLikedVideoByVideoHref(Map<String, Object> params) {
        return super.callStored(NamedStored.FIND_USERS_LIKED_VIDEO_BY_VIDEO_HREF, params);
    }
}
