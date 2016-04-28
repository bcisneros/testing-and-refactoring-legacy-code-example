/**
 * Created on 2009-12-17
 *
 * TimeHelper.java
 *
 * @version 1.0
 * @author zenjian
 * Basic Code
 * @author jinqiu
 * Added Comments
 * @author zenjian
 * Fix bug:CR615 CIM- search date range not working
 * <PRE>
 */
package com.hp.ucmdb.adapter.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class TimeHelper {

    private static final int HOURS = 24;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

    private static Logger logger = Logger.getLogger(TimeHelper.class);

    /**
     * @return Get Current Time.
     * @deprecated Use {@link TimeHelper#todayAsString()} instead of
     */
    public static String getCurrentTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    public static String GetNextDay(String Time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");


        try {
            Date currentTime = formatter.parse(Time);
            currentTime.setHours(currentTime.getHours() + HOURS);
            logger.debug("GetNextDay:" + currentTime);

            return formatter.format(currentTime);
        } catch (ParseException e) {
            CIMLogger.error(e);
        }

        return formatter.format(new Date());

    }

    /**
     * @return Get the before time.
     * @deprecated Use {@link TimeHelper#dateBeforeTodayBy(int)} instead
     */
    public static String GetBeforeTime(int day) {
        Date currentTime = new Date();
        Date beforeTime = new Date();
        beforeTime.setHours(currentTime.getHours() - day * HOURS);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String dateString = formatter.format(beforeTime);
        return dateString;
    }


	public String todayAsString() {
		return formatter.format(today());
	}

	public String dateBeforeTodayBy(int days) {
        return formatter.format(new Date(today().getTime() - TimeUnit.DAYS.toMillis(days)));
		
	}

	protected Date today() {
		return new Date();
	}
}
