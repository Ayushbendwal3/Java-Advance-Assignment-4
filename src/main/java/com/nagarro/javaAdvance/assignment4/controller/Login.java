package com.nagarro.javaAdvance.assignment4.controller;

import com.nagarro.javaAdvance.assignment4.dao.UserDao;
import com.nagarro.javaAdvance.assignment4.model.LoginDetails;
import com.nagarro.javaAdvance.assignment4.model.User;
import com.nagarro.javaAdvance.assignment4.util.AppContextUtil;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("loginedUser")
public class Login {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLogin() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView provideLogin(@Valid @ModelAttribute("loginDetails") LoginDetails loginDetails, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("login");
        if (result.hasErrors()) {
            return modelAndView;
        }

        UserDao obj = (UserDao) AppContextUtil.context.getBean("userDao");
        User user = obj.getUser(loginDetails.getUserId());
        if (user == null) {
            ObjectError error = new ObjectError("loginError", "No such User Exists");
            result.addError(error);
            modelAndView.addObject("InvalidationMsg", "No such User Exists");
            return modelAndView;
        } else if (!user.getPass().equals(loginDetails.getPass())) {
            ObjectError error = new ObjectError("loginError", "Password is incorrect");
            result.addError(error);
            modelAndView.addObject("InvalidationMsg", "Password is incorrect");
            return modelAndView;
        }

        modelAndView = new ModelAndView("redirect:flightSearch");
        modelAndView.addObject("loginedUser", user);
        return modelAndView;
    }
}
