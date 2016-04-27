package com.hp.ucmdb.adapter.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TimeHelperTest {

	private static final String EXPECTED_TODAY_AS_STRING = "2016/04/27";
	private static final int ONE_DAY = 1;
	@Mock
	private Clock clock;
	@Mock
	private CIMLogger cimLogger;

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
		assertThat(timeHelper.todayAsString(), is(EXPECTED_TODAY_AS_STRING));
	}

	@Test
	public void should_throw_an_exception_when_retrieve_days_before_today() throws Exception {
		assertThat(timeHelper.dateAsStringBeforeTodayBy(ONE_DAY), is("2016/04/26"));
	}
	
	@Test
	public void should_retrieve_next_day_of_given_date() throws Exception {
		assertThat(timeHelper.nextDateOf("2016/04/25"), is("2016/04/26"));
	}
	
	@Test
	public void should_retrieve_current_date_when_given_date_has_invalid_format() throws Exception {
		assertThat(timeHelper.nextDateOf("2016-04-25"), is(EXPECTED_TODAY_AS_STRING));
		verify(cimLogger, times(1)).logError(any(Throwable.class));
	}

}
