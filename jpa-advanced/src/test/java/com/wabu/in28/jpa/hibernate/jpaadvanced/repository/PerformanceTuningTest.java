package com.wabu.in28.jpa.hibernate.jpaadvanced.repository;

import com.wabu.in28.jpa.hibernate.jpaadvanced.entity.Course;
import com.wabu.in28.jpa.hibernate.jpaadvanced.entity.Review;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PerformanceTuningTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository repository;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    // with lazy fetch for every row of the query we are firing 1 additional query
    // for a query of 1000 rows we are firing 1001 queries
    public void creatingNplusOneProblem() {
        List<Course> courses =
                em.createQuery("select c from Course c",Course.class).
                        getResultList();
        for(Course course : courses){
            // for every course we are firing additional query for students
            logger.info("Course => {} Students => {}",
                    course,course.getStudents());
        }
    }

    @Test
    @Transactional
    // we can get students as JOIN in a single query without changing to EagerFetch
    public void solvingNplusOneProblem_EntityGraph() {
        EntityGraph<Course> entityGraph = em.createEntityGraph((Course.class));
        entityGraph.addSubgraph("students");
        List<Course> courses =
                em.createQuery("select c from Course c",Course.class)
                        .setHint("javax.persistence.loadgraph", entityGraph)
                        .getResultList();
        for(Course course : courses){
            logger.info("Course => {} Students => {}",
                    course,course.getStudents());
        }
    }

    @Test
    @Transactional
    public void solvingNplusOneProblem_JoinFetch() {
        List<Course> courses =
                em.createQuery("select c from Course c JOIN FETCH c.students",Course.class).
                        getResultList();
        for(Course course : courses){
            logger.info("Course => {} Students => {}",
                    course,course.getStudents());
        }
    }


}