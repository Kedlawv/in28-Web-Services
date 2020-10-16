package com.wabu.spring.basics.springin5steps;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class QuickSort implements SortAlgorithm {
    public int[] sort(int[] numbers) {
        //Logic for Quick Sort
        return numbers;
    }
}
