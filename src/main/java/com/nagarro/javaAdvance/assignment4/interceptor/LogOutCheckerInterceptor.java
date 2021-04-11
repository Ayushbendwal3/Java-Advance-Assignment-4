package com.nagarro.javaAdvance.assignment4.interceptor;

import com.nagarro.javaAdvance.assignment4.model.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutCheckerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginedUser");
        if (user != null) {
            response.sendRedirect("flightSearch");
            return false;
        }
        return true;
    }
}
