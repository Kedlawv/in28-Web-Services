package com.wabu.in28.jpa.hibernate.jpaadvanced.entity;

import javax.persistence.*;

// @MappedSuperclass also an option , works on the DB side like TABLE_PER_CLASS but there is no inheritance relationship
// on the JPA side. We can no longer query ("select e form Employee e") we need to specify the subtype
// @MappedSuperClass and @Entity are exclusive
@Entity
// @Inheritance(strategy=InheritanceType.SINGLE_TABLE) // default strategy - null values - high performance
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS) // separate table for every concrete class but duplicated columns
@Inheritance(strategy=InheritanceType.JOINED) // separate table for the abstract class and concrete classes - low performance
public abstract class Employee {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable=false)
    private String name;

    protected Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
