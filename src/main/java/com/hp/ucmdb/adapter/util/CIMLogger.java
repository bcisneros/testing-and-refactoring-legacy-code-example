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
import org.springframework.stereotype.Component;

@Component
public class CIMLogger {

    private static final Logger ERROR_LOGGER = Logger
            .getLogger(CIMLogger.class);

    public static void error(String msg) {
        ERROR_LOGGER.error(msg);
    }

    public static void error(String msg, Throwable t) {
        ERROR_LOGGER.error(msg, t);
    }

    public static void error(Throwable t) {
        error("", t);
    }
}
