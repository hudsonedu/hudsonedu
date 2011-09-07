package com.googlecode.goodsamples.springbatch.basic;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.item.ItemReader;

import com.googlecode.goodsamples.springbatch.basic.Name;
import com.googlecode.goodsamples.springbatch.basic.NameDAO;
import com.googlecode.goodsamples.springbatch.basic.NameReader;


public class NameReaderTest {
	final Name FIRST_ROW = new Name("Min");
	final Name SECOND_ROW = new Name("Jea");

	ItemReader<Name> O = new NameReader();

	@Before
	public void prepare() {
		LinkedList<Name> expectedRows = new LinkedList<Name>();
		expectedRows.add(FIRST_ROW);
		expectedRows.add(SECOND_ROW);

		NameDAO daoMock = mock(NameDAO.class);
		when(daoMock.selectAll()).thenReturn(expectedRows);

		((NameReader) O).rows = expectedRows;
	}

	@Test
	public void readerShouldReturnExpectedStringsSequentially() throws Exception {
		assertThat(O.read(), is(FIRST_ROW));
		assertThat(O.read(), is(SECOND_ROW));
		assertThat(O.read(), is(nullValue()));
	}
}
