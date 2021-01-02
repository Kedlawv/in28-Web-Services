package com.wabu.mockito;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

public class HamcrestMatchersTest {

    @Test
    public void test(){
        List<Integer> scores = Arrays.asList(99,100,101,105);

        assertThat(scores, hasSize(4));
        assertThat(scores, hasItems(99,100)); //throws NoSuchMethodError most likely library conflict
        assertThat(scores, everyItem(greaterThan(90)));

        assertThat("", isEmptyString()); //same as hasItems
        // solved -> hamcrest dependency has to be first
        // junit and mockito have hamcrest matchers inside and intelliJ goes boom
    }
}
