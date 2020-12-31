package com.in28minutes.junit.helper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


public class ArraysCompareTest {

    @Test
    public void testArraySort_RandomArray() {
        int[] numbers = {12, 3, 4, 1};
        int[] expected = {1, 3, 4, 12};
        Arrays.sort(numbers); // sorts in place

        assertArrayEquals(expected, numbers);
        // will compare contents of the array instead of the object reference

    }

    @Test //(expected=NullPointerException.class) <- JUnit4
    public void testArraySort_NullArray() {
        int[] numbers = null;

        assertThrows(NullPointerException.class, () -> { // <- JUnit5
            Arrays.sort(numbers);
        });
        // takes the type of exception and an executable (lambda)
    }

    @Test
    @Timeout(value=3, unit = TimeUnit.MILLISECONDS)
    public void testArraySortPerformance() {
        int[] array = new int[1000];
        Random rand = new Random();
        for(int i = 0; i<1000; i++){
            array[i] = rand.nextInt(1000);
        }
        Arrays.sort(array);
    }
}
