package com.wabu.data.api;

import java.util.Arrays;
import java.util.List;

//Stubs are very rigid its difficult to test for different conditions
//If the interface grows it will break the test

public class TodoServiceStub implements TodoService{

    public List<String> retrieveTodos(String user) {
        return Arrays.asList("Learn Spring MVC", "Learn Spring","Learn to Dance");
    }
}
