package com.hp.ucmdb.adapter.service;

import static com.hp.ucmdb.adapter.service.GroupCISHandler.BATCH_ID;
import static com.hp.ucmdb.adapter.service.GroupCISHandler.BEFORE_TIME;
import static com.hp.ucmdb.adapter.service.GroupCISHandler.END_TIME;
import static com.hp.ucmdb.adapter.service.GroupCISHandler.PAGE;
import static com.hp.ucmdb.adapter.service.GroupCISHandler.START_TIME;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.hp.ucmdb.adapter.bean.GroupCISBean;
import com.hp.ucmdb.adapter.util.TimeHelper;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class GroupCISHandlerTest {

	private static final String NOT_A_NUMBER_STRING = "not a number";
	private static final String ANY_STRING = "Hello Skippy!";
	private static final String NULL_STRING = null;
	private static final String ANY_BATCH_ID = "Batch id";
	private static final String EMPTY_STRING = "";
	private static final String ANY_START_TIME = null;
	private static final String ANY_END_TIME = null;
	private static final String NEW_STRING_OBJECT = new String();
	
	@Mock
	private TimeHelper timeHelper;
	@Mock
	private Logger logger;
	
	@InjectMocks
	private GroupCISHandler handler = new GroupCISHandler();
	
	private HttpServletRequest request = mock(HttpServletRequest.class);
	
	private String startTime;
	public String today;
	
	@Before
	public void setup() {
		initMocks(this);
	}

	@Test
	@Parameters({ BATCH_ID, START_TIME, END_TIME, PAGE })
	public void should_return_false_when_any_parameter_is_not_null(String parameter) {
		when(request.getParameter(parameter)).thenReturn(ANY_STRING);
		assertThat(handler.shouldGenerateDefaultBean(request), is(false));
	}

	@Test
	public void should_return_true_when_all_parameters_are_null() {
		when(request.getParameter(Mockito.anyString())).thenReturn(NULL_STRING);
		assertThat(handler.shouldGenerateDefaultBean(request), is(true));
	}

	@Test
	public void should_set_up_start_time() throws Exception {
		startTime = "2012/02/15";
		when(timeHelper.dateBeforeTodayBy(BEFORE_TIME)).thenReturn(startTime);
		GroupCISBean bean = handler.handleDefaultParams();
		assertThat(bean.getStartTime(), is(startTime));
	}
	
	@Test
	public void should_set_up_end_time() throws Exception {
		today = "2012/02/15";
		when(timeHelper.todayAsString()).thenReturn(today);
		GroupCISBean bean = handler.handleDefaultParams();
		assertThat(bean.getEndTime(), is(today));
	}
	
	@Test
	public void should_set_up_page() throws Exception {
		GroupCISBean bean = handler.handleDefaultParams();
		assertThat(bean.getPage(), is(1));
	}
	
	@Test
	public void should_set_batch_id_from_request_parameter() throws Exception {
		when(request.getParameter(BATCH_ID)).thenReturn(ANY_BATCH_ID);
		GroupCISBean bean = handler.handleRequestParams(request);
		assertThat(bean.getBatchId(), is(ANY_BATCH_ID));
	}
	
	@Test
	public void should_set_batch_id_to_empty_string_where_parameter_is_null() throws Exception {
		when(request.getParameter(BATCH_ID)).thenReturn(null);
		GroupCISBean bean = handler.handleRequestParams(request);
		assertThat(bean.getBatchId(), is(EMPTY_STRING));
	}
	
	@Test
	public void should_set_batch_id_to_empty_string_where_parameter_is_empty() throws Exception {
		when(request.getParameter(BATCH_ID)).thenReturn(EMPTY_STRING);
		GroupCISBean bean = handler.handleRequestParams(request);
		assertThat(bean.getBatchId(), is(EMPTY_STRING));
	}
	
	@Test
	public void should_set_start_time_from_request_parameter() throws Exception {
		when(request.getParameter(START_TIME)).thenReturn(ANY_START_TIME);
		GroupCISBean bean = handler.handleRequestParams(request);
		assertThat(bean.getStartTime(), is(ANY_START_TIME));
	}
	
	@Test
	public void should_set_end_time_from_request_parameter() throws Exception {
		when(request.getParameter(END_TIME)).thenReturn(ANY_END_TIME);
		GroupCISBean bean = handler.handleRequestParams(request);
		assertThat(bean.getEndTime(), is(ANY_END_TIME));
	}
	
	@Test
	public void should_set_page_from_request_parameter() throws Exception {
		when(request.getParameter(PAGE)).thenReturn("10");
		GroupCISBean bean = handler.handleRequestParams(request);
		assertThat(bean.getPage(), is(10));
	}
	
	@Test
	@Parameters(method="invalidPageValues")
	public void should_set_page_to_one_when_page_parameter_has_invalid_format(String description, String pageValue) throws Exception {
		when(request.getParameter(PAGE)).thenReturn(pageValue/*NOT_A_NUMBER_STRING*/);
		GroupCISBean bean = handler.handleRequestParams(request);
		assertThat(description, bean.getPage(), is(1));
		verify(logger, times(1)).warn(anyString(), any(Throwable.class));
	}
	
	protected Object[] invalidPageValues() {
		return new Object[]{
				new Object[]{"It is not a number", NOT_A_NUMBER_STRING},
				new Object[]{"It is an empty String", EMPTY_STRING},
				new Object[]{"It is an new empty String", NEW_STRING_OBJECT},
				new Object[]{"It is an null String", NULL_STRING},
		};
	}
	
}
