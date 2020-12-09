package com.wabu.in28.database.databasedemo;

import com.wabu.in28.database.databasedemo.entity.Person;
import com.wabu.in28.database.databasedemo.jdbc.PersonJdbcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class DatabaseDemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PersonJdbcDao dao;

	public static void main(String[] args) {
		SpringApplication.run(DatabaseDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		logger.info("All users -> {}", dao.findAll());
		logger.info("User 1001 -> {}", dao.findById(1001));
		logger.info("Users with location 'MiddleEarth' -> {}",
				dao.findByLocation("MiddleEarth"));
		logger.info("Deleting 1005 -> Number of rows Deleted => {}",
				dao.deleteById(1005));
		logger.info("Deleting All From MiddleEarth -> number of rows deleted => {}",
				dao.deleteByLocation("MiddleEarth"));
		Person morpheus = new Person(1006,"Morpheus","Zion", new Date());
		logger.info("insert person: {} -> inserted no of rows {}",morpheus, dao.insert(morpheus));
		morpheus.setLocation("Matrix");
		logger.info("update person -> {} | no of rows updated {}", morpheus, dao.update(morpheus));

	}
}
