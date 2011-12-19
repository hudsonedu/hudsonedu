package com.googlecode.goodsamples.springbatch.partition;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.googlecode.goodsamples.springbatch.basic.Name;

public class NameRowMapper implements RowMapper<Name> {
	public Name mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Name(rs.getInt("id"), rs.getString("name"));
	}
}
