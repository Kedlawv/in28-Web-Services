package com.wabu.in28.jpa.hibernate.jpaadvanced;

import com.wabu.in28.jpa.hibernate.jpaadvanced.entity.Course;
import com.wabu.in28.jpa.hibernate.jpaadvanced.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaAdvancedApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JpaAdvancedApplication.class, args);
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Course course = repository.findById(1001L);
        logger.info("Course 1001 => {}", course);

    }
}
