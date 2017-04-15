package com.roden.spring.jdbc;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet set, int index) throws SQLException {
        Person person = new Person(
                set.getInt("id"),
                set.getString("name"),
                set.getInt("age"),
                set.getString("sex"));
        return person;
    }

}