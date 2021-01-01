package com.wabu.business;

import com.wabu.data.api.TodoService;
import com.wabu.data.api.TodoServiceStub;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoBusinessImplStubTest {

    @Test
    void retrieveTodosRelatedToSpring_usingStub_sizeOfList() {
        TodoService todoServiceStub = new TodoServiceStub();
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceStub);

        List<String> filteredTodos =
                todoBusiness.retrieveTodosRelatedToSpring("Dummy");

        assertEquals(2, filteredTodos.size());
    }

    @Test
    void retrieveTodosRelatedToSpring_usingStub_Contents() {
        TodoService todoServiceStub = new TodoServiceStub();
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceStub);
        List<String> expected = Arrays.asList("Learn Spring MVC", "Learn Spring");

        List<String> filteredTodos =
                todoBusiness.retrieveTodosRelatedToSpring("Dummy");

        assertIterableEquals(expected, filteredTodos);
    }
}