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

    @Test
    void areFirstAndLastTwoCharactersTheSame() {
    }
}