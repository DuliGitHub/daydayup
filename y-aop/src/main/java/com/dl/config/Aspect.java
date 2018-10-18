package com.dl.config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@org.aspectj.lang.annotation.Aspect
@Component//给spring管理
public class Aspect {

    @Pointcut("execution(* com.dl.dao.*.*(..))")
    public void pointCut(){//为什么需要方法，注解需要载体

    }

    @After("com.dl.config.Aspect.pointCut()")
    public void after(){
        System.out.println("after");
    }
    @Before("com.dl.config.Aspect.pointCut()")
    public void before(){
        System.out.println("before");
    }
}
