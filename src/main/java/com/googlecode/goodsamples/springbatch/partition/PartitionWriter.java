package com.googlecode.goodsamples.springbatch.partition;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.googlecode.goodsamples.springbatch.basic.Name;

@Component
public class PartitionWriter implements ItemWriter<Name> {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void write(List<? extends Name> names) throws Exception {
		for (Name eachName : names) {
			String sql = "INSERT INTO targetOfName VALUES (?,?,?)";
			jdbcTemplate.update(sql, eachName.id(), eachName.name(), Thread.currentThread().getId());
		}
	}
}
