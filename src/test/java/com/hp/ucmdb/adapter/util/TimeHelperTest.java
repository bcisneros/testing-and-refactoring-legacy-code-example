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
	public void should_throw_an_exception_when_today_is_retrieving() {
		today = java.sql.Date.valueOf("2016-04-27");
		String todayAsString = timeHelper.todayAsString();
		assertThat(todayAsString, is("2016/04/27"));
	}

	@Test(expected = RuntimeException.class)
	public void should_throw_an_exception_when_retrieve_days_before_today() throws Exception {
		new TimeHelper().dateAsStringBeforeTodayBy(ONE_DAY);
	}

	private class TestableTimeHelper extends TimeHelper {

		@Override
		protected Date today() {
			return today;
		}

	}

}
