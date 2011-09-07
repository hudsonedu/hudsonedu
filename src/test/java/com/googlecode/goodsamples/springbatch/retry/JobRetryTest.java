package com.googlecode.goodsamples.springbatch.retry;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.goodsamples.springbatch.AbstractJobRepositoryInitilization;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/RetryContext.xml" })
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class JobRetryTest extends AbstractJobRepositoryInitilization {
	@Autowired
	JobLauncher jobLauncher;
	@Autowired
	Job job;
	private Integer expectedAttemptedCount = 5;

	@Test
	public void failedJobShouldBeRetriedUntilMaximumCount() throws Exception {
		JobExecution result = jobLauncher.run(job, new JobParameters());
		
		assertThat(result.getStatus(), is(BatchStatus.FAILED));
		assertThat(RetryTasklet.executionCount, is(expectedAttemptedCount));
	}
}