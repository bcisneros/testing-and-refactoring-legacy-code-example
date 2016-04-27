package com.hp.ucmdb.adapter.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TimeHelperTest {

	private static final int ONE_DAY = 1;
	@Mock
	private Clock clock;

	@InjectMocks
	private TimeHelper timeHelper = new TimeHelper();

	public Date today;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		today = java.sql.Date.valueOf("2016-04-27");
		Mockito.when(clock.today()).thenReturn(today);
	}

	@Test
	public void should_throw_an_exception_when_today_is_retrieving() {
		assertThat(timeHelper.todayAsString(), is("2016/04/27"));
	}

	@Test
	public void should_throw_an_exception_when_retrieve_days_before_today() throws Exception {
		assertThat(timeHelper.dateAsStringBeforeTodayBy(ONE_DAY), is("2016/04/26"));
	}

}
