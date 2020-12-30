package com.in28minutes.junit.helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringHelperTest {

    StringHelper stringHelper = new StringHelper();

    @Test
    void truncateAInFirst2Positions_AinFirst2Positions() {
        assertEquals("CD",
                stringHelper.truncateAInFirst2Positions("AACD"));
    }

    @Test
    void truncateAInFirst2Positions_AinFirstPosition() {
        assertEquals("CD",
                stringHelper.truncateAInFirst2Positions("ACD"));
    }
    // absence of failure is a success , no body in the test will result in
    // test success

    @Test
    void areFirstAndLastTwoCharactersTheSame_4LetterFail() {
        assertFalse(stringHelper.areFirstAndLastTwoCharactersTheSame("ABCD"));
    }

    @Test
    void areFirstAndLastTwoCharactersTheSame_4LetterSuccess() {
        assertTrue(stringHelper.areFirstAndLastTwoCharactersTheSame("ABAB"));
    }
}