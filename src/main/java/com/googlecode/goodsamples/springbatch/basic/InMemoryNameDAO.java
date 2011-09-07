package com.googlecode.goodsamples.springbatch.basic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class InMemoryNameDAO implements NameDAO {
	Map<Integer, Name> database = new HashMap<Integer, Name>();
	JdbcTemplate jdbcTemplate;

	public Name insert(Name name) {
		int id = database.size() + 1;
		name.setId(id);
		database.put(id, name);
		return name;
	}

	public Name update(Name toBeUpdated) {
		database.put(toBeUpdated.id(), toBeUpdated);
		return toBeUpdated;
	}

	public Name select(Name name) {
		return database.get(name.id());
	}

	public LinkedList<Name> selectAll() {
		LinkedList<Name> result = new LinkedList<Name>();
		for (Integer key : database.keySet()) {
			result.add(database.get(key));
		}
		return result;
	}

	public void truncate() {
		database.clear();
	}
}
