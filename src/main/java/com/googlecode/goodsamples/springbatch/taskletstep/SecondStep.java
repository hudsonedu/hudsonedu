package com.googlecode.goodsamples.springbatch.taskletstep;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.googlecode.goodsamples.springbatch.basic.Name;
import com.googlecode.goodsamples.springbatch.basic.NameDAO;

@Component
public class SecondStep implements Tasklet {
	private NameDAO nameDAO;

	@Autowired
	public SecondStep(NameDAO nameDAO) {
		this.nameDAO = nameDAO;
	}

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		nameDAO.insert(new Name(1, (String)executionContext(chunkContext).get("1")));
		return RepeatStatus.FINISHED;
	}

	private ExecutionContext executionContext(ChunkContext chunkContext) {
		return chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
	}
}
