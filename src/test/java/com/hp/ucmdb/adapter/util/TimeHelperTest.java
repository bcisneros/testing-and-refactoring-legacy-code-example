package com.hp.ucmdb.adapter.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Test;

public class TimeHelperTest {

	private static final int ONE_DAY = 1;
	private TimeHelper timeHelper = new TestableTimeHelper();
	public Date today;

	@Test
	public void should_throw_an_exception_when_today_is_called() {
		today = java.sql.Date.valueOf("2010-09-17");
		String todayAsString = timeHelper.todayAsString();
		assertThat(todayAsString, is("2010/09/17"));
	}
	
	@Test
	public void should_throw_an_exception_when_date_before_today_by_method() throws Exception {
		today = java.sql.Timestamp.valueOf("2010-09-17 00:00:00");
		String yesterday = timeHelper.dateBeforeTodayBy(ONE_DAY);
		assertThat(yesterday, is("2010/09/16"));
		
	}
	
	private class TestableTimeHelper extends TimeHelper {

		@Override
		protected Date today() {
			return today;
		}
		
	}

}
