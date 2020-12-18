package com.wabu.in28.jpa.hibernate.jpaadvanced.repository;

import com.wabu.in28.jpa.hibernate.jpaadvanced.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
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

    @Test
    @DirtiesContext
    public void sort(){
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        // Sort sort = new Sort(Sort.Direction.ASC, "name");
        // ↑ Constructor for Sort has become protected since the course have been recorded
        List<Course> expectedCourses = new ArrayList<>();
        expectedCourses.add(new Course("C"));
        expectedCourses.add(new Course("B"));
        expectedCourses.add(new Course("A"));
        repository.save(expectedCourses.get(0));
        repository.save(expectedCourses.get(1));
        repository.save(expectedCourses.get(2));

        List<Course> actualCourses = repository.findAll(sort);
        logger.info("\nCourses => {}", actualCourses);

        assertEquals("A", actualCourses.get(0).getName());
        assertEquals("B", actualCourses.get(1).getName());
        assertEquals("C", actualCourses.get(2).getName());
    }

    @Test
    @DirtiesContext
    public void pagination(){
        for(int i=1; i<20; i++){
            repository.save(new Course(String.valueOf(i)));
        }

        PageRequest pageRequest= PageRequest.of(0,3);
        Page<Course> firstPage = repository.findAll(pageRequest);
        List<Course> firstPageContent = firstPage.getContent();

        Pageable secondPageable = firstPage.nextPageable();
        Page<Course> secondPage = repository.findAll(secondPageable);
        List<Course> secondPageContent = secondPage.getContent();

        assertEquals(3, firstPage.getSize());
        assertEquals(3, secondPage.getSize());
    }


}