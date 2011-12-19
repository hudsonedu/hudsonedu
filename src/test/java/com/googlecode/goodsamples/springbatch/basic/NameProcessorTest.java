package com.googlecode.goodsamples.springbatch.basic;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.batch.item.ItemProcessor;

public class NameProcessorTest {
	ItemProcessor<Name, Name> O = new NameProcessor();

	@Test
	public void nameShouldBeModified() throws Exception {
		final Integer id = 1;
		Name name = new Name(id, "Min");

		Name result = O.process(name);

		assertThat(result, is(new Name(id, "Min-modified")));
	}
}
