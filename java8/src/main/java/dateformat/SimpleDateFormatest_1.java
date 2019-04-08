package dateformat;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ThreadLocal可以确保每个线程都可以得到单独的一个SimpleDateFormat的对象，那么自然也就不存在竞争问题了
 */
public class SimpleDateFormatest_1 {

private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>(){
    @Override
    protected DateFormat initialValue() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
};

    public static String formatDate(Date date) throws ParseException {
        return threadLocal.get().format(date);
    }
    public static Date parse(String strDate) throws ParseException{
        return threadLocal.get().parse(strDate);
    }


}
