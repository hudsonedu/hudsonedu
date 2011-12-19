package com.googlecode.goodsamples.springbatch.basic;

import java.util.LinkedList;

//TODO Add an example based on cursor.  
public interface NameDAO {
	Name insert(Name name);

	Name select(Name name);

	Name update(Name toBeUpdated);

	LinkedList<Name> selectAll();
}
