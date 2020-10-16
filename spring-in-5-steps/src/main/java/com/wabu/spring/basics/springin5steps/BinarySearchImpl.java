package com.wabu.spring.basics.springin5steps;

import java.util.Arrays;

public class BinarySearchImpl {

    public int binarySearch(int[] numbers, int numberToSearchFor) {
        Arrays.sort(numbers);
        return Arrays.binarySearch(numbers, numberToSearchFor);
    }
}
