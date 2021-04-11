package com.nagarro.javaAdvance.assignment4.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nagarro.javaAdvance.assignment4.model.User;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheckerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginedUser");
        if (user == null) {
            response.sendRedirect("login");
            return false;
        }
        return true;
    }
}
