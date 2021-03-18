package cn.qzy.web.servlet;

import cn.qzy.domain.PageBean;
import cn.qzy.domain.User;
import cn.qzy.service.UserService;
import cn.qzy.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        if (currentPage==null){
            currentPage="1";
        }
        if (rows==null){
            rows="5";
        }

        Map<String, String[]> condition = request.getParameterMap();
        System.out.println(condition.entrySet().toString());

        UserService userService = new UserServiceImpl();
        PageBean<User> user = userService.findUserByPage(Integer.parseInt(currentPage), Integer.parseInt(rows),condition);
        request.setAttribute("user", user);
        request.setAttribute("condition", condition);
//        Set<String> strings = condition.keySet();
//        for (String string : strings) {
//            System.out.print("key:"+string+"-----value:");
//            System.out.println(condition.get(string)[0]);
//        }
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
