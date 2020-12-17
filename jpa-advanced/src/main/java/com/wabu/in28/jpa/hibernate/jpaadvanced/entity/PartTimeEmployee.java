package com.wabu.in28.jpa.hibernate.jpaadvanced.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class PartTimeEmployee extends Employee{
    private BigDecimal hourlyWage;

    protected PartTimeEmployee() {
    }

    public PartTimeEmployee(String name, BigDecimal hourlyWage) {
        super(name);
        this.hourlyWage = hourlyWage;
    }

    @Override
    public String toString() {
        return super.toString() + "PartTimeEmployee{" +
                "hourlyWage=" + hourlyWage +
                '}';
    }
}
