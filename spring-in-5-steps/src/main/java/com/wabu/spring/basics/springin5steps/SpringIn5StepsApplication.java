package com.wabu.spring.basics.springin5steps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIn5StepsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringIn5StepsApplication.class, args);

        BinarySearchImpl binarySearch = new BinarySearchImpl(new BubbleSort());
        
        int result = binarySearch.binarySearch(new int[]{23, 556, 3, 2, 7}, 2);
        System.out.println("result: " + result);
    }

}
