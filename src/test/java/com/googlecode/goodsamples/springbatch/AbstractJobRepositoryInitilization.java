package com.googlecode.goodsamples.springbatch;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AbstractJobRepositoryInitilization {
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@Before
	public void createJobRepository() throws Exception {
		jdbcTemplate.execute(IOUtils.toString(AbstractJobRepositoryInitilization.class
				.getResourceAsStream("/schema-hsqldb.sql")));
	}

	@After
	public void dropJobRepository() throws Exception {
		jdbcTemplate.execute(IOUtils.toString(AbstractJobRepositoryInitilization.class
				.getResourceAsStream("/schema-drop-hsqldb.sql")));
	}
}
