package com.wabu.in28.jpa.hibernate.jpaadvanced.repository;

import com.wabu.in28.jpa.hibernate.jpaadvanced.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "courses")
public interface CourseSpringDataRepository extends JpaRepository<Course,Long> {

    List<Course> findByName(String name); // methods are implemented by spring according to the name of the method
    int countByName(String name);
    List<Course> findByNameContaining(String name);

    @Query("select c from Course c")
    List<Course> selectAllJPQL();

    @Query(value = "SELECT * FROM COURSE",nativeQuery = true)
    List<Course> selectAllNative();

}
