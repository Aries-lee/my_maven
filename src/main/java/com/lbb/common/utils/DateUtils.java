package com.lbb.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2018/1/13.
 */
public class DateUtils {

    private static Calendar calendar = Calendar.getInstance();

    /**
     *@Author AriesLi [www.coder520.com]
     *@Date 2018/1/13 18:57
     *@Description 计算星期
    */
    public static int getTodayWeek() {
        calendar.setTime(new Date());
        int week = calendar.get(Calendar.DAY_OF_WEEK)-1;
        return week<0?7:week;
    }

    /**
     *@Author AriesLi [www.coder520.com]
     *@Date 2018/1/13 18:57
     *@Description 计算时间差
    */
    public static int getMinute(Date startDate,Date endDate) {
        long start = startDate.getTime();
        long end = endDate.getTime();
        int minute = (int)(end-start)/(1000*60);
        return minute;
    }

    
    /**
     *@Author AriesLi [www.coder520.com]
     *@Date 2018/1/13 19:02
     *@Description 获取当天某个时间点
    */
    public static Date getDate(int hour,int minute) {
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);
        return calendar.getTime();
    }

}
