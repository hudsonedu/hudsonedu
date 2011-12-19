package com.googlecode.goodsamples.springbatch.taskletstep;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

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
import com.googlecode.goodsamples.springbatch.basic.Name;
import com.googlecode.goodsamples.springbatch.basic.NameDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/spring/TaskletStepContext.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class TaskletStepTest extends AbstractJobRepositoryInitilization {
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private Job job;
	@Autowired
	private NameDAO nameDAO;

	@Test
	public void taskletsShouldBeRunStepByStep() throws Exception {
		JobExecution result = jobLauncher.run(job, new JobParameters());

		assertThat(result.getStatus(), is(BatchStatus.COMPLETED));
		assertThat(nameDAO.select(new Name(1, "first")), is(notNullValue()));
	}
}