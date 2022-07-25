package com.codegym.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoAOP {
    @Before("within(com.codegym.service.BlogService.*)")
    public void demo(){
        System.out.println("-------------------");
        System.out.println("-------------------");
        System.out.println("Đã chạy vào đây");
        System.out.println("-------------------");
        System.out.println("-------------------");
    }
}
