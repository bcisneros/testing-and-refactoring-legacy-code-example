package com.hp.ucmdb.adapter.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

public class GroupCISHandlerTest {

	@Test
	public void should_generate_default_bean_when_request_parameters_are_null() {
		GroupCISHandler handler = new GroupCISHandler();
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getParameter(anyString())).thenReturn(null);
		assertThat(handler.shouldGenerateDefaultBean(request), is(true));
	}

}
