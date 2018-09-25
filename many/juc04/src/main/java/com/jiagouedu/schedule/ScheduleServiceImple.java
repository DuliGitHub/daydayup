package com.jiagouedu.schedule;/*
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　永无BUG 　┣┓
 * 　　　　┃　　如来保佑　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┗┻┛　┗┻┛
 */

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleServiceImple implements ScheduleService {

  ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);

  @Override
  public void startJob(int seconds) {
    executorService.scheduleWithFixedDelay( new Monitor(),0,seconds, TimeUnit.SECONDS);
  }

  @Override
  public void shutDown() {
    executorService.shutdown();
  }
}
