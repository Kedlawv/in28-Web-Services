package com.in28minutes.junit.helper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class StringHelperParameterizedTest {

    StringHelper stringHelper = new StringHelper();

    @ParameterizedTest
    @CsvSource(value = {"AACD:CD","ACD:CD","CDEF:CDEF","CDAA:CDAA"},delimiter = ':')
    void truncateAInFirst2Positions_Parameterized(String input, String expected) {
        assertEquals(expected,
                stringHelper.truncateAInFirst2Positions(input));
    }


}