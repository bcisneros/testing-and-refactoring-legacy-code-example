package com.hp.ucmdb.adapter.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class CIMLoggerTest {

	@Test(expected = RuntimeException.class)
	public void should_throw_an_exception_when_log_an_error() {
		new CIMLogger().logError(new Throwable());
	}

}
