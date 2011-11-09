package com.googlecode.goodsamples.springbatch.basic;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.item.ItemWriter;

public class NameWriterTest {
	ItemWriter<Name> O = new NameWriter();
	NameDAO nameDAO = mock(InMemoryNameDAO.class);

	@Before
	public void prepareMock() {
		((NameWriter)O).nameDAO = nameDAO;
	}

	@Test
	public void givenNamesShouldBePersistedToDatabase() throws Exception {
		List<Name> names = new ArrayList<Name>();
		Name name = new Name(id(1), name("Min"));
    int hash_code = name.hashCode();
		names.add(name);

		O.write(names);

    assertEquals(78354,hash_code);
		verify(nameDAO).update(name);
	}

	private String name(String name) {
		return name;
	}

	private Integer id(int id) {
		return id;
	}
}
