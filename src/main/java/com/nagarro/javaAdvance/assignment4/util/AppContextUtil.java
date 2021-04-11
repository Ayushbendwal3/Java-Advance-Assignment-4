package com.nagarro.javaAdvance.assignment4.util;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContextUtil {
    public static AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
}
