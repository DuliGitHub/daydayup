package com.dl.test;

import com.dl.config.Appconfig;
import com.dl.dao.IndexDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Appconfig.class);
//        applicationContext.start();
        IndexDao dao = applicationContext.getBean(IndexDao.class);
        dao.query();

    }
}
