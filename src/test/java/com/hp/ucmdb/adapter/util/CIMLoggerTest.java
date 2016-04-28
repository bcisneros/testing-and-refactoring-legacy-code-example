package com.hp.ucmdb.adapter.util;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CIMLoggerTest {
	
	@Mock
	Logger logger;
	
	@InjectMocks
	CIMLogger cimLogger = new CIMLogger();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void should_log_errors() {
		Throwable throwable = new Throwable();
		cimLogger.logError(throwable);
		verify(logger, times(1)).error(anyString(), eq(throwable));
	}

}
