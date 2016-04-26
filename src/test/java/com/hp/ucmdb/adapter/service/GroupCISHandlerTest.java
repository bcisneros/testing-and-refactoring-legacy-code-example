package com.hp.ucmdb.adapter.service;

import static com.hp.ucmdb.adapter.service.GroupCISHandler.BATCH_ID;
import static com.hp.ucmdb.adapter.service.GroupCISHandler.END_TIME;
import static com.hp.ucmdb.adapter.service.GroupCISHandler.PAGE;
import static com.hp.ucmdb.adapter.service.GroupCISHandler.START_TIME;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class GroupCISHandlerTest {

	GroupCISHandler handler = new GroupCISHandler();
	HttpServletRequest request = mock(HttpServletRequest.class);
	
	private static final String ANY_STRING = "any string";

	@Test
	public void should_generate_default_bean_when_request_parameters_are_null() {
		when(request.getParameter(anyString())).thenReturn(null);
		assertThat(handler.shouldGenerateDefaultBean(request), is(true));
	}
	
	@Test
	@Parameters({BATCH_ID, START_TIME, END_TIME, PAGE})
	public void should_not_generate_default_bean_when_parameter_is_not_null(String parameter) throws Exception {
		when(request.getParameter(parameter)).thenReturn(ANY_STRING);
		assertThat(handler.shouldGenerateDefaultBean(request), is(false));
	}
}
