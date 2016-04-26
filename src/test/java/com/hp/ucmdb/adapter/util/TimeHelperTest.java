package com.hp.ucmdb.adapter.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class TimeHelperTest {

	private static final int ONE_DAY = 1;

	@Test(expected = RuntimeException.class)
	public void should_throw_an_exception_when_today_is_retrieving() {
		new TimeHelper().todayAsString();
	}
	
	@Test(expected = RuntimeException.class)
	public void should_throw_an_exception_when_retrieve_days_before_today() throws Exception {
		new TimeHelper().dateAsStringBeforeTodayBy(ONE_DAY);
	}

}
