package com.hp.ucmdb.adapter.service;

import static com.hp.ucmdb.adapter.service.GroupCISHandler.BATCH_ID;
import static com.hp.ucmdb.adapter.service.GroupCISHandler.END_TIME;
import static com.hp.ucmdb.adapter.service.GroupCISHandler.PAGE;
import static com.hp.ucmdb.adapter.service.GroupCISHandler.START_TIME;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hp.ucmdb.adapter.bean.GroupCISBean;
import com.hp.ucmdb.adapter.util.TimeHelper;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class GroupCISHandlerTest {

	@Mock
	TimeHelper timeHelper;
	@InjectMocks 
	private GroupCISHandler realHandler = new GroupCISHandler();
	
	HttpServletRequest request = mock(HttpServletRequest.class);
	public String startTime;
	public String today;
	
	private static final String ANY_STRING = "any string";
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void should_generate_default_bean_when_request_parameters_are_null() {
		when(request.getParameter(anyString())).thenReturn(null);
		assertThat(realHandler.shouldGenerateDefaultBean(request), is(true));
	}
	
	@Test
	@Parameters({BATCH_ID, START_TIME, END_TIME, PAGE})
	public void should_not_generate_default_bean_when_parameter_is_not_null(String parameter) throws Exception {
		when(request.getParameter(parameter)).thenReturn(ANY_STRING);
		assertThat(realHandler.shouldGenerateDefaultBean(request), is(false));
	}
	
	@Test
	public void should_setup_start_time_by_default_three_days() throws Exception {
		startTime = "2015/09/10";
		when(timeHelper.dateAsStringBeforeTodayBy(GroupCISHandler.THREE_DAYS_BEFORE)).thenReturn(startTime);
		GroupCISBean bean = realHandler.handleDefaultParams();
		assertThat(bean.getStartTime(), is(startTime));
	}
	
	@Test
	public void should_setup_end_time_with_actual_day() throws Exception {
		today = "2015/09/13";
		when(timeHelper.todayAsString()).thenReturn(today);
		GroupCISBean bean = realHandler.handleDefaultParams();
		assertThat(bean.getEndTime(), is(today));
	}
	
	@Test
	public void should_setup_page_to_one() throws Exception {
		GroupCISBean bean = realHandler.handleDefaultParams();
		assertThat(bean.getPage(), is(1));
	}
	
}
