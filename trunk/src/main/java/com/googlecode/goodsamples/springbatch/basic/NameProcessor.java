package com.googlecode.goodsamples.springbatch.basic;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class NameProcessor implements ItemProcessor<Name, Name> {
	public Name process(Name item) throws Exception {
		return new Name(item.id(), item.name + "-modified");
	}
}
