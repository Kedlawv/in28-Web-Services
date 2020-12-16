package com.wabu.in28.jpa.hibernate.jpaadvanced.repository;

import com.wabu.in28.jpa.hibernate.jpaadvanced.entity.Course;
import com.wabu.in28.jpa.hibernate.jpaadvanced.entity.Passport;
import com.wabu.in28.jpa.hibernate.jpaadvanced.entity.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentRepository repository;

    @Autowired
    EntityManager em;

    @Test
    @DirtiesContext
    void saveWithPassport() {
        String name = "Frodo";
        String passportNo = "ME000001";

        Student student = repository.saveWithPassport(name, passportNo);
        Student studentDb = repository.findById(student.getId());

        assertEquals(name, student.getName(), "Name from returned Student doesn't match");
        assertEquals(passportNo, student.getPassport().getNumber(),
                "passport number from returned student doesn't match");
        assertEquals(name, studentDb.getName(),
                "name from retrieved student doesn't match");
        assertEquals(passportNo, studentDb.getPassport().getNumber(),
                "passport number from retrieved student doesn't match");
    }

    @Test
    public void findById() {
        Student student = repository.findById(2001L);

        assertEquals("Kedlaw", student.getName());
    }

    @Test
    @Transactional
    public void retrievePassportAndAssociatedStudent(){
        Passport passport = em.find(Passport.class, 4001L);
        Student student = passport.getStudent();

        assertEquals("12B31234",passport.getNumber());
        assertEquals("Kedlaw",student.getName());
    }

    @Test
    @Transactional
    public void retrieveStudentAndCourses(){
        Student student = em.find(Student.class, 2001L);
        logger.info("\n-----------Student => {}", student); // lazy fetch

        List<Course> actualCourses = student.getCourses(); // we can do this thanks to @Transactional

        assertEquals("JPA Advanced", actualCourses.get(0).getName());
        assertEquals("Spring Boot in 100 steps", actualCourses.get(1).getName());
    }

    @Test
    @Transactional
    public void retrieveCourseAndStudents(){
        Course course = em.find(Course.class, 1001L);

        List<Student> students = course.getStudents();

        assertEquals("Kedlaw",students.get(0).getName());
        assertEquals("Grzegorz",students.get(1).getName());
        assertEquals("Ewa",students.get(2).getName());

    }

    @Test
    @Transactional
    public void enrollStudent(){
        Student student = em.find(Student.class, 2001L);
        Course course = em.find(Course.class, 1002L);

        repository.enrollStudent(student,course);
        Student actualStudent = em.find(Student.class, 2001L);

        assertTrue(actualStudent.getCourses().contains(course));
    }

}