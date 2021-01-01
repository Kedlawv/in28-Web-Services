package com.wabu.business;

import com.wabu.data.api.TodoService;
import com.wabu.data.api.TodoServiceStub;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TodoBusinessImplMockTest {

    @Test
    void retrieveTodosRelatedToSpring_usingMock_sizeOfList() {
        TodoService todoServiceMock = mock(TodoService.class);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceMock);
        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

        List<String> filteredTodos =
                todoBusiness.retrieveTodosRelatedToSpring("Dummy");

        assertEquals(2, filteredTodos.size());
    }

    @Test
    void retrieveTodosRelatedToSpring_usingMock_EmptyList(){
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> mockReturn = new ArrayList<String>();
        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(mockReturn);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceMock);
        
        assertEquals(0, todoBusiness.retrieveTodosRelatedToSpring("Dummy").size());
    }

    @Test
    void retrieveTodosRelatedToSpring_usingMock_Contents() {
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> mockServiceReturn = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        List<String> expected = Arrays.asList("Learn Spring MVC", "Learn Spring");
        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(mockServiceReturn);

        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceMock);

        List<String> filteredTodos =
                todoBusiness.retrieveTodosRelatedToSpring("Dummy");

        assertIterableEquals(expected, filteredTodos);
    }
}