package com.googlecode.goodsamples.springbatch.taskletstep;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class FirstStep implements Tasklet {
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		executionContext(chunkContext).put("1", "first");
		return RepeatStatus.FINISHED;
	}

	private ExecutionContext executionContext(ChunkContext chunkContext) {
		return chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
	}
}
