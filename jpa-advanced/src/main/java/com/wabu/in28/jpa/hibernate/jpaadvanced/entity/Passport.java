package com.wabu.in28.jpa.hibernate.jpaadvanced.entity;

import javax.persistence.*;

@Entity
public class Passport {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String number;

    // ↓ Bi-direction relationship Student->Passport Passport->Student
    // mappedBy attribute sets which entity owns the relationship as in which table the data will be stored
    // without mappedBy and OneToOne on both entities we will have duplication of data
    // mappedBy is used on the entity NOT owning the relationship (which field should map to a column)
    @OneToOne(fetch=FetchType.LAZY, mappedBy = "passport")
    private Student student;

    protected Passport(){

    }

    public Passport(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
