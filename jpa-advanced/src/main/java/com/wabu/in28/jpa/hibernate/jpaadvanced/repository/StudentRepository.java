package com.wabu.in28.jpa.hibernate.jpaadvanced.repository;

import com.wabu.in28.jpa.hibernate.jpaadvanced.entity.Passport;
import com.wabu.in28.jpa.hibernate.jpaadvanced.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class StudentRepository {

    @Autowired
    EntityManager em;

    public Student findById(Long id){
        return em.find(Student.class,id);
    }

    public Student saveWithPassport(String name, String passportNo){
        Passport passport = new Passport(passportNo);
        em.persist(passport); // needs to be persisted before creating relationship with the student
                                // This is all happening withing the persistence context
                                // nothing is inserted in to the DB yet
                                // Hibernate acts in a Lazy way , DB operations are triggered when we exit the method

        Student student = new Student(name);
        student.setPassport(passport);

        em.persist(student);
        return student;
    }
}
