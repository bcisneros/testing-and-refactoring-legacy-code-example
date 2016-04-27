package com.hp.ucmdb.adapter.util;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class CIMLoggerTest {

	@Mock
	Logger logger;

	@InjectMocks
	CIMLogger cimLogger = new CIMLogger();

	@Before
	public void setUp() {
		initMocks(this);
	}

	@Test
	public void should_throw_an_exception_when_log_an_error() {
		cimLogger.logError(new Throwable());
		verify(logger, times(1)).error(any(), any(Throwable.class));
	}

}
