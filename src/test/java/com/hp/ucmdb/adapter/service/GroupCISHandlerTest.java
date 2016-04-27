package com.hp.ucmdb.adapter.service;

import static com.hp.ucmdb.adapter.service.GroupCISHandler.BATCH_ID;
import static com.hp.ucmdb.adapter.service.GroupCISHandler.END_TIME;
import static com.hp.ucmdb.adapter.service.GroupCISHandler.PAGE;
import static com.hp.ucmdb.adapter.service.GroupCISHandler.START_TIME;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hp.ucmdb.adapter.bean.GroupCISBean;
import com.hp.ucmdb.adapter.util.TimeHelper;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class GroupCISHandlerTest {

	private static final String ANY_DATE_STRING = "2015/09/10";
	@Mock
	TimeHelper timeHelper;
	@Mock
	Logger logger;
	
	@InjectMocks
	private GroupCISHandler handler = new GroupCISHandler();

	HttpServletRequest request = mock(HttpServletRequest.class);
	public String startTime;
	public String today;

	private static final String ANY_STRING = "any string";
	private static final String EMPTY_STRING = "";

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void should_generate_default_bean_when_request_parameters_are_null() {
		when(request.getParameter(anyString())).thenReturn(null);
		assertThat(handler.shouldGenerateDefaultBean(request), is(true));
	}

	@Test
	@Parameters({ BATCH_ID, START_TIME, END_TIME, PAGE })
	public void should_not_generate_default_bean_when_parameter_is_not_null(String parameter) throws Exception {
		when(request.getParameter(parameter)).thenReturn(ANY_STRING);
		assertThat(handler.shouldGenerateDefaultBean(request), is(false));
	}

	@Test
	public void should_setup_start_time_by_default_three_days() throws Exception {
		startTime = ANY_DATE_STRING;
		when(timeHelper.dateAsStringBeforeTodayBy(GroupCISHandler.THREE_DAYS_BEFORE)).thenReturn(startTime);
		GroupCISBean bean = handler.handleDefaultParams();
		assertThat(bean.getStartTime(), is(startTime));
	}

	@Test
	public void should_setup_end_time_with_actual_day() throws Exception {
		today = "2015/09/13";
		when(timeHelper.todayAsString()).thenReturn(today);
		GroupCISBean bean = handler.handleDefaultParams();
		assertThat(bean.getEndTime(), is(today));
	}

	@Test
	public void should_setup_page_to_one() throws Exception {
		GroupCISBean bean = handler.handleDefaultParams();
		assertThat(bean.getPage(), is(1));
	}

	@Test
	public void should_set_batch_id_from_the_request_parameter() throws Exception {
		when(request.getParameter(BATCH_ID)).thenReturn(ANY_STRING);
		GroupCISBean bean = handler.handleRequestParams(request);
		assertThat(bean.getBatchId(), is(ANY_STRING));
	}

	@Test
	public void should_set_batch_id_to_empty_string_when_request_parameter_is_empty() throws Exception {
		when(request.getParameter(BATCH_ID)).thenReturn(EMPTY_STRING);
		GroupCISBean bean = handler.handleRequestParams(request);
		assertThat(bean.getBatchId(), is(EMPTY_STRING));
	}

	@Test
	public void should_set_start_time_from_the_request_parameter() throws Exception {
		when(request.getParameter(START_TIME)).thenReturn(ANY_DATE_STRING);
		GroupCISBean bean = handler.handleRequestParams(request);
		assertThat(bean.getStartTime(), is(ANY_DATE_STRING));
	}

	@Test
	public void should_set_end_time_from_the_request_parameter() throws Exception {
		when(request.getParameter(END_TIME)).thenReturn(ANY_DATE_STRING);
		GroupCISBean bean = handler.handleRequestParams(request);
		assertThat(bean.getEndTime(), is(ANY_DATE_STRING));
	}

	@Test
	public void should_set_page_from_the_request_parameter() throws Exception {
		when(request.getParameter(PAGE)).thenReturn("2");
		GroupCISBean bean = handler.handleRequestParams(request);
		assertThat(bean.getPage(), is(2));
	}

	@Test
	public void should_set_page_to_one_when_request_parameter_is_null() throws Exception {
		when(request.getParameter(PAGE)).thenReturn(null);
		GroupCISBean bean = handler.handleRequestParams(request);
		assertThat(bean.getPage(), is(1));
	}

	@Test
	public void should_set_page_to_one_when_request_parameter_is_empty() throws Exception {
		when(request.getParameter(PAGE)).thenReturn(EMPTY_STRING);
		GroupCISBean bean = handler.handleRequestParams(request);
		assertThat(bean.getPage(), is(1));
	}
	
	@Test
	public void should_set_page_to_one_when_request_parameter_is_empty_new_string() throws Exception {
		when(request.getParameter(PAGE)).thenReturn(new String(""));
		GroupCISBean bean = handler.handleRequestParams(request);
		assertThat(bean.getPage(), is(1));
	}
	
	@Test
	public void should_set_page_to_one_when_request_parameter_is_not_an_integer() throws Exception {
		when(request.getParameter(PAGE)).thenReturn("not an integer string");
		GroupCISBean bean = handler.handleRequestParams(request);
		assertThat(bean.getPage(), is(1));
		verify(logger, times(1)).warn(anyString());
	}

}
