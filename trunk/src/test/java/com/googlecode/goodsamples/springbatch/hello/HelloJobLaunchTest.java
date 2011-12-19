package com.googlecode.goodsamples.springbatch.hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.goodsamples.springbatch.AbstractJobRepositoryInitilization;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/HelloContext.xml" })
@TransactionConfiguration
@Transactional
public class HelloJobLaunchTest extends AbstractJobRepositoryInitilization {
	@Autowired
	JobLauncher jobLauncher;
	@Autowired
	Job helloJob;

	@Test
	public void jobShouldBeRunWithoutAnyException() throws Exception {
		 jobLauncher.run(helloJob, new JobParameters());
	}
}