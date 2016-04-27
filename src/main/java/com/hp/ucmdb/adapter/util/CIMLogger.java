/**
 * Created on 2009-12-23
 *
 * CIMLogger.java
 *
 * @version 1.0
 * @author zenjian
 * Basic Code
 * @author jinqiu
 * Added Comments
 *
 * <PRE>
 */

package com.hp.ucmdb.adapter.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class CIMLogger {

	@Autowired
	private Logger ERROR_LOGGER;

	public static void error(String msg) {
		new CIMLogger().ERROR_LOGGER.error(msg);
	}

	public static void error(String msg, Throwable t) {
		new CIMLogger().ERROR_LOGGER.error(msg, t);
	}

	/**
	 * 
	 * @param t
	 * @deprecated Use {@link CIMLogger#logError(Throwable)} instead of
	 */
	public static void error(Throwable t) {
		error("", t);
	}

	public void logError(Throwable throwable) {
		ERROR_LOGGER.error("",throwable);

	}
	
	@Bean
	protected Logger getLogger() {
		return Logger.getLogger(getClass());
	}
	
	
}
