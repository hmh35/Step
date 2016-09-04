package cn.edu.fjnu.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author: linqiu
 * @Date: 2016/3/5 20:32
 * @Description: 日期通用类
 */
public class DateUtils {

    /**
     * 当前日期加一个月
     */
    public static Date laterAMonth() {
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println("today is:" + format.format(Calendar.getInstance().getTime()));
        //System.out.println("yesterday is:" + format.format(cal.getTime()));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        Date date = cal.getTime();
        return date;
    }

    /**
     * date1 是否大于 date2
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isLater(Date date1, Date date2) {
        int result = date1.compareTo(date2);
        if (result == 1) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Date date1 = new Date();
        Date date2 = laterAMonth();
        //System.out.println(date2.compareTo(date1)); //date2大->1
        System.out.println(isLater(date2,date1));
    }
}
