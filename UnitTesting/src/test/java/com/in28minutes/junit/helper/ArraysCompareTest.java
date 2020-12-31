package com.in28minutes.junit.helper;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
}
