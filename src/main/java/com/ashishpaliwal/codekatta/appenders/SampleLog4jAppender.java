package com.ashishpaliwal.codekatta.appenders;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Simple Sample appender
 */
public class SampleLog4jAppender extends AppenderSkeleton {
    @Override
    protected void append(LoggingEvent loggingEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void close() {
        // NOOP do nothong
    }

    @Override
    public boolean requiresLayout() {
        return false;
    }
}
