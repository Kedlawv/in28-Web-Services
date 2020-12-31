package com.in28minutes.junit.helper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuickBeforeAfterTest {

    @BeforeEach //Course is using JUnit4 | @BeforeEach in JUnit5 has replaced @Before in JUnit4
    public void setup(){
        System.out.println("Before Test");
    }

    @Test
    public void test1(){
        System.out.println("Test one executed");
    }

    @Test
    public void test2(){
        System.out.println("Test two executed");
    }

    @AfterEach
    public void teardown(){
        System.out.println("After test");
    }
}
