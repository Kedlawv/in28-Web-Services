package com.wabu.in28.database.databasedemo;

import com.wabu.in28.database.databasedemo.entity.Person;
import com.wabu.in28.database.databasedemo.jpa.PersonJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonJpaRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        logger.info("User 1001 -> {}", repository.findById(1001));

//		logger.info("All users -> {}", repository.findAll());
//		logger.info("Users with location 'MiddleEarth' -> {}",
//				repository.findByLocation("MiddleEarth"));
        logger.info("Deleting 1005 ");
        repository.deleteById(1005);
//		logger.info("Deleting All From MiddleEarth -> number of rows deleted => {}",
//				repository.deleteByLocation("MiddleEarth"));
        Person morpheus = new Person("Morpheus", "Zion", new Date());
        logger.info("insert person: {} -> \n person inserted {}", morpheus, repository.insert(morpheus));
        morpheus = repository.findById(1);
        morpheus.setLocation("Matrix");
        logger.info("update person -> {} | \n person updated {}", morpheus, repository.update(morpheus));

    }
}
