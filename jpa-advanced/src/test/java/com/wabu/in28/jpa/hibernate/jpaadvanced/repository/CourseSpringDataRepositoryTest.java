package com.wabu.in28.jpa.hibernate.jpaadvanced.repository;

import com.wabu.in28.jpa.hibernate.jpaadvanced.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseSpringDataRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseSpringDataRepository repository;

    @Test
    public void findById_CoursePresent() {
        Optional<Course> courseOptional = repository.findById(1001L);
        logger.info("{}",courseOptional.isPresent());

        assertTrue(courseOptional.isPresent());
    }

    @Test
    public void findById_CourseNotPresent() {
        Optional<Course> courseOptional = repository.findById(2001L);
        logger.info("{}",courseOptional.isPresent());

        assertFalse(courseOptional.isPresent());
    }

    @Test
    public void saveAndUpdate(){
        Course course = new Course("Testing...");
        repository.save(course);
        logger.info("\n---Course => {}", course);
        course.setName("Testing... Updated");
        repository.save(course);
        
        Optional<Course> actualCourse = repository.findById(course.getId());
        if(!actualCourse.isPresent()) { fail("Item not found!");}
        assertEquals("Testing... Updated", actualCourse.get().getName());
    }


}