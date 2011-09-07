package com.googlecode.goodsamples.springbatch.partition;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.database.JdbcCursorItemReader;

public class LoggableJdbcCursorItemReader<T> extends JdbcCursorItemReader<T> {
	private static final Log LOG = LogFactory.getLog(LoggableJdbcCursorItemReader.class);
	
	@Override
	protected T doRead() throws Exception {
		LOG.info("CurrentThreadID : " + Thread.currentThread().getId());
		return super.doRead();
	}

}
