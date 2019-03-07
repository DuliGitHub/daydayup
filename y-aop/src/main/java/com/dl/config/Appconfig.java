package com.dl.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.dl")
@EnableAspectJAutoProxy //<aop:aspectj-autoproxy/>
public class Appconfig {
}
