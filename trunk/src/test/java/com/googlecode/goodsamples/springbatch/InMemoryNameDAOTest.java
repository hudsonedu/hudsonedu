package com.googlecode.goodsamples.springbatch;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.googlecode.goodsamples.springbatch.basic.InMemoryNameDAO;
import com.googlecode.goodsamples.springbatch.basic.Name;
import com.googlecode.goodsamples.springbatch.basic.NameDAO;

public class InMemoryNameDAOTest {
	NameDAO O = new InMemoryNameDAO();
	Name name = new Name("Min");

	@Before
	public void insert() {
		Name result = O.insert(name);
		assertNotNull(result.id());
		assertTrue(result == name);
	}

	@After
	public void rollback() {
		((InMemoryNameDAO) O).truncate();
	}

	@Test
	public void select() {
		assertTrue(name == O.select(new Name(name.id())));
	}

	@Test
	public void update() {
		name.setName("modifiedMin");
		assertTrue(name == O.update(name));
	}

	@Test
	public void selectAll() {
		LinkedList<Name> result = O.selectAll();

		assertThat(result.size(), is(1));
		result.poll();
		assertThat(result.poll(), is(nullValue()));
	}
}
