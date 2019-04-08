package dateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimpleDateFormatest {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //线程安全问题
//    public static String formatDate(Date date) throws ParseException {
//        return sdf.format(date);
//    }
//    public static Date parse(String strDate) throws ParseException{
//        return sdf.parse(strDate);
//    }


    //解决方案一    加重创建对象的负担，效率低
//    public static String formatDate(Date date) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return sdf.format(date);
//    }
//    public static Date parse(String strDate) throws ParseException{
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return sdf.parse(strDate);
//    }


    /**
     *  解决方案二   线程阻塞
     */

    public static String formatDate(Date date) throws ParseException {
       synchronized (sdf){
           return sdf.format(date);
       }

    }
    public static Date parse(String strDate) throws ParseException{
        synchronized (sdf){
            return sdf.parse(strDate);
        }

    }



    public static void main(String[] args) throws InterruptedException {
        System.out.println(sdf.format(new Date()));//单线程没有问题

        ExecutorService service = Executors.newFixedThreadPool(100);
        for(int i = 0;i < 20;i++){
            service.execute(
                    ()->{
                        try {
                            System.out.println(parse("2018-01-02 09:45:59"));
                        }catch (ParseException e){
                            e.printStackTrace();
                        }
                    }
            );
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.DAYS);
    }
}
