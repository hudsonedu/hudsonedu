package com.googlecode.goodsamples.springbatch.basic;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NameWriter implements ItemWriter<Name> {
	@Autowired
	NameDAO nameDAO;

	public void write(List<? extends Name> items) throws Exception {
		for (Name eachName : items) {
			nameDAO.update(eachName);
		}
	}
}
