package com.googlecode.goodsamples.springbatch.partition;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
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
@ContextConfiguration(locations = {"/META-INF/spring/PartitionContext.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class JobPartitionTest extends AbstractJobRepositoryInitilization {
	private static final int ROWCOUNT = 10;
	@Autowired
	JobLauncher jobLauncher;
	@Autowired
	Job job;

	@Test
	public void jobShouldBeRunOnAtLeastTwoDistinctSlaves() throws Exception {
		insertTenRowsToNameTable();

		jobLauncher.run(job, new JobParameters());

		assertThat(rowCountOfNameTable(), is(ROWCOUNT));
		assertThat(usedSlavesCount(), is(greaterThan(2)));
	}

	private int usedSlavesCount() {
		return jdbcTemplate.queryForList("SELECT DISTINCT(worker) FROM targetOfName").size();
	}

	private int rowCountOfNameTable() {
		return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM targetOfName");
	}

	private void insertTenRowsToNameTable() {
		String sqlToInsert = "INSERT INTO name VALUES (?, ?);";
		for (int i = 0; i < ROWCOUNT; i++) {
			jdbcTemplate.update(sqlToInsert, i, "Name_" + i);
		}
	}

	private void createTargetTableWhichHasIdAndNameAndWorker() {
		String sqlToCreateTarget = "CREATE TABLE targetOfName (id INT NOT NULL PRIMARY KEY, name VARCHAR(50), worker VARCHAR(50));";
		jdbcTemplate.update(sqlToCreateTarget);
	}

	private void createNameTableWhichHasIdAndName() {
		String sqlToCreate = "CREATE TABLE name (id INT NOT NULL PRIMARY KEY, name VARCHAR(50));";
		jdbcTemplate.update(sqlToCreate);
	}

	@Before
	public void createTables() {
		createNameTableWhichHasIdAndName();
		createTargetTableWhichHasIdAndNameAndWorker();
	}

	@After
	public void dropNameTable() {
		jdbcTemplate.update("DROP TABLE name");
		jdbcTemplate.update("DROP TABLE targetOfName");
	}
}