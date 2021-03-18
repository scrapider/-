package cn.qzy.dao.impl;

import cn.qzy.dao.UserDao;
import cn.qzy.domain.User;
import cn.qzy.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    @Override
    public void add(User user) {
        System.out.println(user);
        String sql = "insert  into user values (?,?,?,?,?,?,?);";
        template.update(sql, null, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
    }

    @Override
    public void delete(int id) {
        String sql = "delete from user where id=?";
        template.update(sql, id);
    }

    @Override
    public void update(User user) {
        System.out.println(user + "nihao");
        String sql = "update user set name = ?,gender = ? ,age = ? , address = ? , qq = ?, email = ? where id = ?";
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }

    @Override
    public User findUserById(int id) {
        String sql = "select * from user where id = ?";
        User users = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        return users;
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
//        String sql = "select count(*) from user WHERE 1=1";
//        StringBuilder sb = new StringBuilder(sql);
//        Set<String> strings = condition.keySet();
//        List<Object> params = new ArrayList<>();
//        for (String string : strings) {
//            if("currentPage".equals(string) || "rows".equals(string)){
//                continue;
//            }
//            String values = condition.get(string)[0];
//            if (values != null && !"".equals(values)){
//                sb.append(" and "+string+" like ? ");
//                params.add("%"+values+"%");
//            }
//        }
//        System.out.println(sb.toString());
//        System.out.println(params);
//        System.out.println(sql);
//        return template.queryForObject(sql, Integer.class,params.toArray());
        String sql = "select count(*) from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params = new ArrayList<>();
        for (String key : keySet) {

            //排除分页条件参数
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if (value != null && !"".equals(value)) {
                //有值
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");//？条件的值
            }
        }
        System.out.println(sb.toString());
        System.out.println(params);

        return template.queryForObject(sb.toString(), Integer.class, params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
//        String sql = "select * from user limit ?,?";
//        return template.query(sql, new BeanPropertyRowMapper<>(User.class),start,rows);
//        String sql = "select count(*) from user where 1 = 1 ";
//        StringBuilder sb = new StringBuilder(sql);
//        //2.遍历map
//        Set<String> keySet = condition.keySet();
//        //定义参数的集合
//        List<Object> params = new ArrayList<Object>();
//        for (String key : keySet) {
//
//            //排除分页条件参数
//            if("currentPage".equals(key) || "rows".equals(key)){
//                continue;
//            }
//
//            //获取value
//            String value = condition.get(key)[0];
//            //判断value是否有值
//            if(value != null && !"".equals(value)){
//                //有值
//                sb.append(" and "+key+" like ? ");
//                params.add("%"+value+"%");//？条件的值
//            }
//        }
//        System.out.println(sb.toString());
//        System.out.println(params);
//
//        sb.append(" limit ?,? ");
//        params.add(start);
//        params.add(rows);
//
//        return template.query(sb.toString(),new BeanPropertyRowMapper<>(User.class),params.toArray());
//    }
        String sql = "select * from user  where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {

            //排除分页条件参数
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if (value != null && !"".equals(value)) {
                //有值
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");//？条件的值
            }
        }

        //添加分页查询
        sb.append(" limit ?,? ");
        //添加分页查询参数值
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        System.out.println(sql);
        System.out.println(params);

        return template.query(sql, new BeanPropertyRowMapper<User>(User.class), params.toArray());

    }
}
