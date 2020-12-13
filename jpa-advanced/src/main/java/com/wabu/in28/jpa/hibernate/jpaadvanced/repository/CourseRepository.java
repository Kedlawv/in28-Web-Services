package com.wabu.in28.jpa.hibernate.jpaadvanced.repository;

import com.wabu.in28.jpa.hibernate.jpaadvanced.entity.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CourseRepository {

    Logger logger = LoggerFactory.getLogger(this.getClass());

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

    public Course playWithEm(){
        Course course = new Course("em tracking");
        logger.info("\n---after creation id => {}", course.getId());

        em.persist(course);
        logger.info("\n---after persisting id => {}", course.getId());

        // triggers db update ↓
        // because our class is @Transactional all methods are treated as transactions
        // and because em is tracking the created Course entity.
        // Any changes to the entity within the transaction are persisted
        // even if there is no explicit call to em.merge()
        course.setName("em tracking - updated");
        // em.detach(course); remove the object from em tracking
        // em.clear(); remove all objects from em tracking
        // em.flush() -> pushes changes of the object to the db
        // em.refresh() -> is pulling data of the object from the db


        return course;
    }

}
