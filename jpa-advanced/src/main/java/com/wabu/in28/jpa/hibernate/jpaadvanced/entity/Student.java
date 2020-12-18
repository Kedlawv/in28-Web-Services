package com.wabu.in28.jpa.hibernate.jpaadvanced.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreType
@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    private Passport passport; // JPA will create a column named passport_id because of the OneToOne relationship
                                // it will also set the passport field as a foreign key in the db

    @ManyToMany
    @JoinTable(name="STUDENT_COURSE",
    joinColumns = @JoinColumn(name="STUDENT_ID"),
    inverseJoinColumns = @JoinColumn(name="COURSE_ID"))
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    List<Review> reviews = new ArrayList<>();

    protected Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course){
        courses.add(course);
    }

    public void removeCourse(Course course){
        courses.remove(course);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review){
        reviews.add(review);
    }

    public void removeReview(Review review){
        reviews.remove(review);
    }



    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
