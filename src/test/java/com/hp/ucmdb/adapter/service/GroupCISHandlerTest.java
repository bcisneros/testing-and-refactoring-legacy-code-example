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
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class GroupCISHandlerTest {

	private static final String ANY_STRING = "Hello Skippy!";
	private static final String NULL_STRING = null;
	private GroupCISHandler handler = new GroupCISHandler();
	private HttpServletRequest request = mock(HttpServletRequest.class);

	@Test
	@Parameters({ BATCH_ID, START_TIME, END_TIME, PAGE })
	public void should_return_false_when_batch_id_parameter_is_not_null(String parameter) {
		when(request.getParameter(parameter)).thenReturn(ANY_STRING);
		assertThat(handler.shouldGenerateDefaultBean(request), is(false));
	}

	@Test
	public void should_return_true_when_all_parameters_are_null() {
		when(request.getParameter(Mockito.anyString())).thenReturn(NULL_STRING);
		assertThat(handler.shouldGenerateDefaultBean(request), is(true));
	}

}
