package com.wabu.in28.jpa.hibernate.jpaadvanced.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "course") // Lazy fetch by default (all ToMany)
    private List<Review> reviews = new ArrayList<>();

    // ↓ With ManyToMany relationship one side needs to own the relationship to avoid duplicating the
    // joint tables (STUDENT_COURSES COURSES_STUDENTS) in this case it doesn't matter which side
    // as data is put in a new table not a column of the entities table
    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    protected Course(){

    }

    public Course(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
    }

    @JsonIgnore
    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public void removeStudent(Student student){
        students.remove(student);
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "\nCourse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
