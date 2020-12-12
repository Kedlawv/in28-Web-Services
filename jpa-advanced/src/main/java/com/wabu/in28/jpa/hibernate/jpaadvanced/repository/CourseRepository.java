package com.wabu.in28.jpa.hibernate.jpaadvanced.repository;

import com.wabu.in28.jpa.hibernate.jpaadvanced.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CourseRepository {

    @Autowired
    EntityManager em;

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        em.remove(course);
    }

    public Course save(Course course) { // in previous example we used em.merge() for both insert and update
        if (course.getId() == null) {   // with explanation that em will perform the appropriate action.
            em.persist(course);         // Inconsistent
        } else {
            em.merge(course);
        }
        return course;
    }

}
