package com.wabu.spring.basics.springin5steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //Turns class into a Spring managed bean
public class BinarySearchImpl {

    @Autowired // Tells Spring that SortAlgorithm is a dependency
    private SortAlgorithm sortAlgorithm;

    public BinarySearchImpl(SortAlgorithm sortAlgorithm) {
        this.sortAlgorithm = sortAlgorithm;
    }


    public int binarySearch(int[] numbers, int numberToSearchFor) {

        System.out.println(sortAlgorithm);
        int[] sortedNumbers = sortAlgorithm.sort(numbers);
        return 111; // mock number
    }
}
