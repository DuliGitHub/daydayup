package com.jiagouedu.test;/* ━━━━━━如来保佑━━━━━━
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
 * 以往视频加小乔老师QQ：895900009
 * 悟空老师QQ：245553999
 */

import com.jiagouedu.concurrent.ThreadPoolExecutor;

import java.util.concurrent.LinkedBlockingQueue;

public class Test {
   public static void main(String[] args) {
      ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, new LinkedBlockingQueue<>());
      for (int i=0;i<10;i++) {
         threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
               System.out.println("悟空活好人帅");
            }
         });

      }

   }
}
