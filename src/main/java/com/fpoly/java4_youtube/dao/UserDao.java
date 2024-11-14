package com.fpoly.java4_youtube.dao;

import com.fpoly.java4_youtube.entity.User;
import java.util.List;

public interface UserDao {
    User findById(Integer id);

    User findByEmail(String email);

    User findByUserName(String username);

    User findByUsernameAndPassword(String username, String password);

    List<User> findAll();

    List<User> findAll(int pageNumber, int pageSize);

    User create(User entity);

    User update(User entity);

    User delete(User entity);
}
