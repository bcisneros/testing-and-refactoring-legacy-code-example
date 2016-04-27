package com.hp.ucmdb.adapter.service;

import static com.hp.ucmdb.adapter.service.GroupCISHandler.BATCH_ID;
import static com.hp.ucmdb.adapter.service.GroupCISHandler.END_TIME;
import static com.hp.ucmdb.adapter.service.GroupCISHandler.PAGE;
import static com.hp.ucmdb.adapter.service.GroupCISHandler.START_TIME;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.mockito.Mockito;

public class GroupCISHandlerTest {

	private static final String ANY_STRING = "Hello Skippy!";
	private static final String NULL_STRING = null;
	private GroupCISHandler handler = new GroupCISHandler();
	private HttpServletRequest request = mock(HttpServletRequest.class);

	@Test
	public void should_return_false_when_batch_id_parameter_is_not_null() {
		when(request.getParameter(BATCH_ID)).thenReturn(ANY_STRING);
		assertThat(handler.shouldGenerateDefaultBean(request), is(false));
	}
	
	@Test
	public void should_return_false_when_start_time_parameter_is_not_null() {
		when(request.getParameter(START_TIME)).thenReturn(ANY_STRING);
		assertThat(handler.shouldGenerateDefaultBean(request), is(false));
	}
	
	@Test
	public void should_return_false_when_end_time_parameter_is_not_null() {
		when(request.getParameter(END_TIME)).thenReturn(ANY_STRING);
		assertThat(handler.shouldGenerateDefaultBean(request), is(false));
	}
	
	@Test
	public void should_return_false_when_page_parameter_is_not_null() {
		when(request.getParameter(PAGE)).thenReturn(ANY_STRING);
		assertThat(handler.shouldGenerateDefaultBean(request), is(false));
	}
	
	@Test
	public void should_return_true_when_all_parameters_are_null() {
		when(request.getParameter(Mockito.anyString())).thenReturn(NULL_STRING);
		assertThat(handler.shouldGenerateDefaultBean(request), is(true));
	}

}
