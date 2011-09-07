package com.googlecode.goodsamples.springbatch.continuation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;
import org.springframework.stereotype.Component;

@Component
public class ContinuationStreamItemReader extends AbstractItemCountingItemStreamItemReader<Integer> {
	List<Integer> source = new ArrayList<Integer>();
	Integer position = 0;
	
	{
		makeTenItems();
		setName("Retry Job");
	}

	@Override
	protected Integer doRead() throws Exception {
		final Integer endOfItems = null; 
		if (source.size() == position) {
			return endOfItems;
		}
		return source.get(position++);
	}

	@Override
	protected void doOpen() throws Exception {
		position = 0;
	}

	@Override
	protected void doClose() throws Exception {
	}

	private void makeTenItems() {
		for (int i = 1;i <= 10; i++) {
			source.add(i);
		}
	}	
}
