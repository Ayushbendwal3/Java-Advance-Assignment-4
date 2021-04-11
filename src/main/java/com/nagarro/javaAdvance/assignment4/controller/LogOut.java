package com.nagarro.javaAdvance.assignment4.controller;

import com.nagarro.javaAdvance.assignment4.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LogOut {

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView getLogin(HttpSession session) {
        User user = (User) session.getAttribute("loginedUser");
        System.out.println("logging out the " + user.getUserId());

        session.removeAttribute("loginedUser");
        return new ModelAndView("redirect:login");
    }
}
