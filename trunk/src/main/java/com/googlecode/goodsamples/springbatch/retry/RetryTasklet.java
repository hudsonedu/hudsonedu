package com.googlecode.goodsamples.springbatch.retry;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class RetryTasklet implements Tasklet {
	static int executionCount = 0;

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		executionCount++;
		throw new SomeBatchRelatedException();
	}
}
