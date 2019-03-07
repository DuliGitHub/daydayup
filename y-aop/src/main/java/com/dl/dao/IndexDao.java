package com.dl.dao;

import org.springframework.stereotype.Component;

@Component
//public class IndexDao {
public class IndexDao implements Dao{
    public void query(){
        System.out.println("dao--------------query ");
    }
}
