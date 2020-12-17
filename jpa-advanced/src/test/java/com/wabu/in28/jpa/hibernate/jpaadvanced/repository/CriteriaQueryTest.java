package com.wabu.in28.jpa.hibernate.jpaadvanced.repository;

import com.wabu.in28.jpa.hibernate.jpaadvanced.entity.Course;
import com.wabu.in28.jpa.hibernate.jpaadvanced.entity.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class CriteriaQueryTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void criteriaQueryRetrieveAll(){
        // 1. Use CriteriaBuilder to create a CriteriaQuery returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        // 2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);
        // 3. Define Predicates etc using CriteriaBuilder
        // 4. Add Predicates etc to the Criteria Query
        // 5. Build the TypedQuery using the entity manager and criteria query

        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();

        assertEquals(3,resultList.size());
    }

    @Test
    public void criteriaQueryHaving100StepsInName(){
        // 1. Use CriteriaBuilder to create a CriteriaQuery returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        // 2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);
        // 3. Define Predicates etc using CriteriaBuilder
        Predicate like100Steps = cb.like(courseRoot.get("name"), "%100 steps");
        // 4. Add Predicates etc to the Criteria Query
        cq.where(like100Steps);
        // 5. Build the TypedQuery using the entity manager and criteria query

        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();

        assertEquals(1003L,resultList.get(0).getId());
    }

    @Test
    public void criteriaQueryAllCoursesWithoutStudents(){
        // 1. Use CriteriaBuilder to create a CriteriaQuery returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        // 2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);
        // 3. Define Predicates etc using CriteriaBuilder
        Predicate studentIsEmpty = cb.isEmpty(courseRoot.get("students"));
        // 4. Add Predicates etc to the Criteria Query
        cq.where(studentIsEmpty);
        // 5. Build the TypedQuery using the entity manager and criteria query

        //SELECT course.id from course where course.id not in (select course_ID from STUDENT_course)
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();

        assertEquals(1002L,resultList.get(0).getId());
    }

    @Test
    public void criteriaQueryJoinLeft(){
        // 1. Use CriteriaBuilder to create a CriteriaQuery returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        // 2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);
        // 3. Define Predicates etc using CriteriaBuilder
        Join<Object, Object> join = courseRoot.join("students",JoinType.LEFT);
        // 4. Add Predicates etc to the Criteria Query
        // 5. Build the TypedQuery using the entity manager and criteria query

        //SELECT course.id from course where course.id not in (select course_ID from STUDENT_course)
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();

        logger.info("ResultList {}" , resultList);
    }
}
