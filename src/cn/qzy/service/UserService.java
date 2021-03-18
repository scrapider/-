package cn.qzy.service;


import cn.qzy.domain.PageBean;
import cn.qzy.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> findAll();

    void add(User user);

    void delete(int id);

    void update(User user);

    User findUserById(int id);

    void deleteUsers(String[] ids);

    PageBean<User> findUserByPage(int currentPage, int rows, Map<String, String[]> condition);
}
