package com.hp.ucmdb.adapter.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Test;

public class ClockTest {

	@Test
	public void should_return_current_date() {
		assertThat(new Clock().today(), equalTo(new Date()));
	}

}
