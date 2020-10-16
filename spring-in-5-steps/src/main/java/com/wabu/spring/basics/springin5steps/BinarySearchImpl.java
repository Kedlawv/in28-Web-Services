package com.wabu.spring.basics.springin5steps;

public class BinarySearchImpl {

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
