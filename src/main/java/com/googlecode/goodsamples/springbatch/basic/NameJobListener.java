package com.googlecode.goodsamples.springbatch.basic;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class NameJobListener implements JobExecutionListener {
	public void afterJob(JobExecution jobExecution) {
		// TODO Test needed
	}

	public void beforeJob(JobExecution jobExecution) {
		// TODO Test needed
	}
}
