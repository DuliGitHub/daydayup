package com.jiagouedu.schedule;
public class Test {
    public static void main(String[] args) throws InterruptedException {
        ScheduleServiceImple scheduledExecutor = new ScheduleServiceImple();
        scheduledExecutor.startJob(2);

    }
}
