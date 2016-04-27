package com.hp.ucmdb.adapter.service;

import static com.hp.ucmdb.adapter.service.GroupCISHandler.BATCH_ID;
import static com.hp.ucmdb.adapter.service.GroupCISHandler.END_TIME;
import static com.hp.ucmdb.adapter.service.GroupCISHandler.PAGE;
import static com.hp.ucmdb.adapter.service.GroupCISHandler.START_TIME;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.hp.ucmdb.adapter.bean.GroupCISBean;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class GroupCISHandlerTest {

	private static final String ANY_STRING = "Hello Skippy!";
	private static final String NULL_STRING = null;
	private GroupCISHandler handler = new TestableGroupCISHandler();
	private HttpServletRequest request = mock(HttpServletRequest.class);
	private String startTime;
	public String today;

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
		GroupCISBean bean = handler.handleDefaultParams();
		assertThat(bean.getStartTime(), is(startTime));
	}
	
	@Test
	public void should_set_up_end_time() throws Exception {
		today = "2012/02/15";
		GroupCISBean bean = handler.handleDefaultParams();
		assertThat(bean.getEndTime(), is(today));
	}
	
	@Test
	public void should_set_up_page() throws Exception {
		GroupCISBean bean = handler.handleDefaultParams();
		assertThat(bean.getPage(), is(1));
	}
	
	private class TestableGroupCISHandler extends GroupCISHandler {

		@Override
		protected String getStartTime() {
			return startTime;
		}

		@Override
		protected String today() {
			return today;
		}
		
		
		
	}

}
