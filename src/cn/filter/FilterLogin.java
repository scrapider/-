package cn.qzy.web.servlet.filter;

import org.omg.CORBA.ServerRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter({"/add.jsp", "/day17/*","/error.jsp","/index.jsp","/list.jsp","/update.jsp"})
public class FilterLogin implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("filter run");
        boolean flag = false;
        Cookie[] cookies = (( HttpServletRequest ) request).getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            if (name.equals("zhangsa2n") && value.equals("zhangsan")) {
                flag = true;
            }

        }
        if (flag) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
