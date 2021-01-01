package com.wabu.business;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {

    @Test
    public void mockListSizeMethod() {
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(2);

        assertEquals(2, listMock.size());
    }

    @Test
    public void mockListSizeMethod_ReturnMultipleValues() {
        List mockList = mock(List.class);
        when(mockList.size()).thenReturn(2).thenReturn(3);
        // first time mockList.size() is called it will return 2 the second
        // time it's called it will return 3

        assertEquals(2, mockList.size());
        assertEquals(3, mockList.size());
    }

    @Test
    public void mockListGetMethod() {
        List mockList = mock(List.class);
        when(mockList.get(0)).thenReturn("wabu");

        assertEquals("wabu", mockList.get(0));
        assertEquals(null, mockList.get(1)); // when we don't stub the method
        // we get a default value in this case null
    }

    @Test
    public void mockListGetMethod_withArgumentMatcher() {
        List mockList = mock(List.class);
        when(mockList.get(anyInt())).thenReturn("wabu");

        assertEquals("wabu", mockList.get(0));
        assertEquals("wabu", mockList.get(1));
    }

    @Test
    public void mockListGetMethod_throwsException() {
        List mockList = mock(List.class);
        when(mockList.get(0)).thenThrow(new RuntimeException("Error!"));

        assertThrows(RuntimeException.class, () -> mockList.get(0));
    }
}
