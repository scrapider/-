package cn.qzy.web.servlet;

import cn.qzy.domain.User;
import cn.qzy.service.UserService;
import cn.qzy.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Integer id = new Integer(null);
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String age = request.getParameter("age");
        String address = request.getParameter("address");
        String qq = request.getParameter("qq");
        String email = request.getParameter("email");
//        User user = new User(id, name, sex, Integer.parseInt(age), address, qq, email);
//        Map<String, String[]> map = request.getParameterMap();
        //3.封装对象
        Map<String,String> map = new HashMap<>();
        map.put("name", name);
        map.put("gender", gender);
        map.put("age", age);
        map.put("address", address);
        map.put("qq", qq);
        map.put("email", email);
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService userService = new UserServiceImpl();
        userService.add(user);
        request.getRequestDispatcher("/findUserByPageServlet").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
