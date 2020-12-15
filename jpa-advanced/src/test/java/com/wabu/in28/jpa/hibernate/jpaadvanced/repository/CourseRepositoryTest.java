package com.wabu.in28.jpa.hibernate.jpaadvanced.repository;

import com.wabu.in28.jpa.hibernate.jpaadvanced.entity.Course;
import com.wabu.in28.jpa.hibernate.jpaadvanced.entity.Review;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository repository;

    @Autowired
    EntityManager em;

    @Test
    public void findById_basic() {
        Course course = repository.findById(1001L);
        logger.info("\nTest is running");
        assertEquals("JPA Advanced", course.getName());
    }

    @Test
    @DirtiesContext
        // spring will reset data before running other tests
    void deleteById() {
        Long idToDelete = 1001L;
        Course course = repository.findById(idToDelete);
        assertNotNull(course, "\nid => " + idToDelete + " was not found");

        repository.deleteById(idToDelete);
        course = repository.findById(idToDelete);
        assertNull(course);
    }

    @Test
    @DirtiesContext
    public void save() {
        Course newCourse = new Course("Test");
        Course savedCourse = repository.save(newCourse);
        assertEquals("Test",
                repository.findById(savedCourse.getId()).getName(),
                "Insert failed!");

        savedCourse.setName("Test Update");
        repository.save(savedCourse);
        assertEquals("Test Update",
                repository.findById(savedCourse.getId()).getName(),
                "Update failed!");
    }

    @Test
    @DirtiesContext
    public void playWithEm() {
        repository.playWithEm();
    }

    @Test
    public void nativeQuireWithParameter() {
        Query queryPositionalParam = em.createNativeQuery("SELECT * FROM COURSE WHERE id=?", Course.class);
        Query queryNamedParam = em.createNativeQuery("SELECT * FROM COURSE WHERE id= :id", Course.class);
        queryPositionalParam.setParameter(1, 1001L);
        queryNamedParam.setParameter("id", 1001L);

        List<Course> resultList = queryPositionalParam.getResultList();
        logger.info("\n -- SELECT * FROM COURSE WHERE id=1001 \n=> {}", resultList.get(0).getName());
        assertEquals("JPA Advanced", resultList.get(0).getName());
    }

    @Test
    @Transactional
    public void getReviewsFromCourse(){
        Course course = em.find(Course.class, 1001L);
        List<Review> reviews = course.getReviews();

        assertEquals("JPA Advanced",course.getName());
        assertEquals("Great Course",reviews.get(0).getDescription());
    }
}