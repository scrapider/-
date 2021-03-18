package cn.qzy.service.impl;

import cn.qzy.dao.UserDao;
import cn.qzy.dao.impl.UserDaoImpl;
import cn.qzy.domain.PageBean;
import cn.qzy.domain.User;
import cn.qzy.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User findUserById(int id) {
        return userDao.findUserById(id);
    }

    @Override
    public void deleteUsers(String[] ids) {
        for (String id : ids) {
            int i = Integer.parseInt(id);
            userDao.delete(i);
        }
    }

    @Override
    public PageBean<User> findUserByPage(int currentPage, int rows, Map<String, String[]> condition) {
        PageBean<User> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setCurrentPage(currentPage);
        int totalCount = userDao.findTotalCount(condition);
        pageBean.setTotalCount(totalCount);
        List<User> byPage = userDao.findByPage((currentPage - 1) * rows, rows,condition);
        pageBean.setList(byPage);
        pageBean.setTotalPage(totalCount % rows == 0 ? totalCount / rows : totalCount / rows + 1);
        return pageBean;
    }
}
