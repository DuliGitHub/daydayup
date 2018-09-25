package com.jiagouedu.atomic.filed;/*
 * ━━━━━━如来保佑━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━永无BUG━━━━━━
 * 图灵学院-悟空老师
 * www.jiagouedu.com
 * 悟空老师QQ：245553999
 */

import com.jiagouedu.util.TlUtil;

public class AtomicIntegerFieldUpdater {
    /***
     *顾名思义支持每个类中的字段cas操作
     * 只支持加volatile和int 不支持Integer
     */
   static final java.util.concurrent.atomic.AtomicIntegerFieldUpdater<Student> atomicIntegerFieldUpdater= java.util
           .concurrent.atomic.AtomicIntegerFieldUpdater.newUpdater(Student.class,"score");
    public static void main(String[] args) {
        final Student student=new Student();
        TlUtil.timeTasks(100, 10, new Runnable() {
            @Override
            public void run() {
                atomicIntegerFieldUpdater.incrementAndGet(student);
            }
        });
        System.out.println(student.score);

    }



}

class Student{
  String name;
  volatile int score;
}