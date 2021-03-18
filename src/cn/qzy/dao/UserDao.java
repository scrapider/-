package cn.qzy.dao;

import cn.qzy.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    List<User> findAll();
    void add(User user);
    void delete(int id);
    void update(User user);
    User findUserById(int id);
    int findTotalCount(Map<String, String[]> condition);
    List findByPage(int start, int rows, Map<String, String[]> condition);
}
