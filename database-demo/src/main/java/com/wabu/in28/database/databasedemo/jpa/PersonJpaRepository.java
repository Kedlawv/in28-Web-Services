package com.wabu.in28.database.databasedemo.jpa;

import com.wabu.in28.database.databasedemo.entity.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PersonJpaRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Person findById(int id){
        return entityManager.find(Person.class,id);
    }

    // for update and insert we use entityManager.merge | the entityManager will perform appriopriate action
    // depending on whether id is set or not

    public Person update(Person person){
        return entityManager.merge(person);
    }

    public Person insert(Person person){
        return entityManager.merge(person);
    }
}
