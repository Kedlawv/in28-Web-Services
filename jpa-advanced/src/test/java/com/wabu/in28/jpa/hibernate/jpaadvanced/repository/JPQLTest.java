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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class JPQLTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void jpqlGetCoursesWithoutStudents(){
        TypedQuery<Course> query = em.createQuery(
                //select all fields of c(Course) from all instances of Course alias c where c.students is empty
                "select c from Course c where c.students is empty ",Course.class);
        List<Course> resultList = query.getResultList();

        assertEquals("Spring in 50 steps",resultList.get(0).getName());
    }

    @Test
    public void jpqlGetCoursesWithAtLeastTwoStudents(){
        TypedQuery<Course> query = em.createQuery(
                // SQL ↓
                // select * from (select course_id , count(*) as num
                // from student_course group by course_id) as num_students
                // where num_students.num >= 2 -> gets correct row in H2 but Hibernate's query looks a lot different
                "select c from Course c where size(c.students) >= 2",Course.class);

        List<Course> resultList = query.getResultList();

        assertEquals(1001,resultList.get(0).getId());
    }

    @Test
    public void jpqlGetCoursesOrderedByNumberOfStudents(){
        TypedQuery<Course> query = em.createQuery(
                // select course_id, count(*) as num
                // from student_course
                // group by course_id order by num <= do not trust this, you need to brush up on SQL
                "select c from Course c order by size(c.students)",Course.class);

        List<Course> resultList = query.getResultList();
        logger.info("/n --------ResultList => {}", resultList);


        assertEquals(1002L, resultList.get(0).getId()); // JPQL return a course with 0 students while SQL on the DB does not
        assertEquals(1003L, resultList.get(1).getId());
        assertEquals(1001L, resultList.get(2).getId());
    }

    @Test
    public void jpqlStudentsWithPassportNumbersInACertainPattern(){
        TypedQuery<Student> query = em.createQuery(
                //select * from
                //(SELECT student.id,name,passport_id,number FROM STUDENT, passport where student.passport_id=passport.id)
                //where number like '%1234%'
                "select s from Student s where s.passport.number like '%1234%'",Student.class);
        List<Student> resultList = query.getResultList();

        logger.info("\n -----Students => {}", resultList);

        assertEquals("12B31234", resultList.get(0).getPassport().getNumber());
        assertEquals("LM123465", resultList.get(1).getPassport().getNumber());
    }

    @Test
    public void join(){
        Query query = em.createQuery("select c,s from Course c JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        for(Object[] result:resultList){
            logger.info("\n ----Course => {} Student => {}", result[0], result[1]);
        }
    }

    @Test
    public void joinLeft(){
        Query query = em.createQuery("select c,s from Course c LEFT JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        for(Object[] result:resultList){
            logger.info("\n ----Course => {} Student => {}", result[0], result[1]);
        }
    }



}
