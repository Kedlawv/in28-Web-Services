package com.wabu.in28.jpa.hibernate.jpaadvanced.repository;

import com.wabu.in28.jpa.hibernate.jpaadvanced.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository repository;

    @Test
    public void findById_basic() {
        Course course = repository.findById(1001L);
        logger.info("\nTest is running");
        assertEquals("JPA Advanced", course.getName());
    }

    @Test
    @DirtiesContext   // spring will reset data before running other tests
    void deleteById() {
        Long idToDelete = 1001L;
        Course course = repository.findById(idToDelete);
        assertNotNull(course, "\nid => " + idToDelete + " was not found");

        repository.deleteById(idToDelete);
        course = repository.findById(idToDelete);
        assertNull(course);

    }
}