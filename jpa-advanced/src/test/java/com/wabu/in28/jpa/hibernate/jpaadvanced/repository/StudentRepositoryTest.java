package com.wabu.in28.jpa.hibernate.jpaadvanced.repository;

import com.wabu.in28.jpa.hibernate.jpaadvanced.entity.Passport;
import com.wabu.in28.jpa.hibernate.jpaadvanced.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

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
}