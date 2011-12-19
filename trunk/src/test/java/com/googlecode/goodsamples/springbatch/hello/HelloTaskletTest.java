package com.googlecode.goodsamples.springbatch.hello;

import static org.mockito.Mockito.*;

import org.apache.commons.logging.Log;
import org.junit.Test;
import org.springframework.batch.core.step.tasklet.Tasklet;



public class HelloTaskletTest {
	Tasklet O = new HelloTasklet();
	
	@Test
	public void taskletShouldSayHelloThroughLog() throws Exception {
		Log log = mock(Log.class);
		((HelloTasklet)O).log = log;
		
		O.execute(null, null);
		
		verify(log).info("Hello");
	}
}
