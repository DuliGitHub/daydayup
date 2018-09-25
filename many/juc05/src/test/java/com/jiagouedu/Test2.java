package com.jiagouedu.concurrent2;/* ━━━━━━如来保佑━━━━━━
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


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class Test2 {
   public static void main(String[] args) throws ExecutionException, InterruptedException {
     ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, new LinkedBlockingQueue<>());
/*      for (int i=0;i<10;i++) {
         Future<Object> future = threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
               System.out.println("悟空活好人帅");
            }
         });


      }*/
      for (int i=0;i<10;i++) {
         Future<Object> future = threadPoolExecutor.submit(new Callable() {
            @Override
            public Object call() throws Exception {
               return "悟空活好人帅";
            }
         });
         System.out.println(future.get());


      }

   }


}
