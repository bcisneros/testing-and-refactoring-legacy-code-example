package com.hp.ucmdb.adapter.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class TimeHelperTest {

	private static final int ONE_DAY = 1;
	
	@Mock
	private Clock clock;
	
	@Mock
	private CIMLogger cimLogger;
	
	@InjectMocks
	private TimeHelper timeHelper;
	
	public Date today;

	@Before
	public void setup() {
		initMocks(this);
		today = java.sql.Date.valueOf("2010-09-17");
		when(clock.today()).thenReturn(today);
	}
	
	@Test
	public void should_return_the_actual_date_in_given_format() {
		assertThat(timeHelper.todayAsString(), is("2010/09/17"));
	}
	
	@Test
	public void should_throw_an_exception_when_date_before_today_by_method() throws Exception {
		assertThat(timeHelper.dateBeforeTodayBy(ONE_DAY), is("2010/09/16"));
	}
	
	@Test
	public void should_return_the_next_day_for_a_given_date() throws Exception {
		String myBirthDay = "2010/09/25";
		assertThat(timeHelper.nextDateOf(myBirthDay), is("2010/09/26"));
	}
	
	@Test
	public void should_return_the_actual_date_when_format_is_wrong() throws Exception {
		String invalidDate = "2010-09-25";
		String now = "2010/09/17";
		assertThat(timeHelper.nextDateOf(invalidDate), is(now));
		verify(cimLogger, times(1)).logError(any(Throwable.class));
	}

}
