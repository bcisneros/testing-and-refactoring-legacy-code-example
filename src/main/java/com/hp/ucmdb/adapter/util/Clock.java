package com.hp.ucmdb.adapter.util;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Clock {

	public Date today() {
		throw new RuntimeException("Unit test could not call this");
	}

}
