package com.wabu.in28.database.databasedemo.jdbc;

import com.wabu.in28.database.databasedemo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonJdbcDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Person> findAll(){
        return jdbcTemplate.query("select * from person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person findById(int id){
        return jdbcTemplate.queryForObject("select * from person where id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class));
    }

    public List<Person> findByLocation(String location){
        return jdbcTemplate.query("select * from person where location = ?",
                new Object[]{location},
                new BeanPropertyRowMapper<>(Person.class));
    }
}
