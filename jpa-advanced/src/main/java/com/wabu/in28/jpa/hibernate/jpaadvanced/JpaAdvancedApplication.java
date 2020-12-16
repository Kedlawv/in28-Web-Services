package com.wabu.in28.jpa.hibernate.jpaadvanced;

import com.wabu.in28.jpa.hibernate.jpaadvanced.entity.Course;
import com.wabu.in28.jpa.hibernate.jpaadvanced.entity.Review;
import com.wabu.in28.jpa.hibernate.jpaadvanced.repository.CourseRepository;
import com.wabu.in28.jpa.hibernate.jpaadvanced.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JpaAdvancedApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JpaAdvancedApplication.class, args);
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
//        Course course = repository.findById(1001L);
//        logger.info("Course 1001 => {}", course);
//        logger.info("\nSaving a new Course... \n Course saved => {}",
//                repository.save(new Course("Microservices in 100 steps")));
//        Course microCourse = repository.findById(1L);
//        microCourse.setName("Microservices in 150 steps");
//        logger.info("\nUpdating Course ... \nCourse updated => {}",
//                repository.save(microCourse));

        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review("5", "All we have to decide is what to do with the time that is given us"));
        reviews.add(new Review("5", "You shall most certainly pass"));
        studentRepository.saveWithPassport("Frodo","ME000001");
        courseRepository.addReviewForCourse(1002L,"5","Great Hands-on Stuff");
        courseRepository.addReviewsForCourse(1002L, reviews);
        courseRepository.addReviewForCourse(1001L,"5","In-depth",2002L);
    }
}
