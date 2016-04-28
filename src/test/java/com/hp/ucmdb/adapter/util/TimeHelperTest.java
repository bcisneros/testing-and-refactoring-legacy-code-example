package com.hp.ucmdb.adapter.util;

import org.junit.Test;

public class TimeHelperTest {

	private static final int ONE_DAY = 1;

	@Test(expected = RuntimeException.class)
	public void should_throw_an_exception_when_today_is_called() {
		new TimeHelper().today();
	}
	
	@Test(expected = RuntimeException.class)
	public void should_throw_an_exception_when_date_before_today_by_method() throws Exception {
		new TimeHelper().dateBeforeTodayBy(ONE_DAY);
	}

}
