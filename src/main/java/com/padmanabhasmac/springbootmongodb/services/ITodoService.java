package com.padmanabhasmac.springbootmongodb.services;

import com.padmanabhasmac.springbootmongodb.exceptions.TodoCollectionException;
import com.padmanabhasmac.springbootmongodb.models.TodoDTO;
import jakarta.validation.ConstraintViolationException;

public interface ITodoService {

    void createTodo(TodoDTO todoDTO) throws ConstraintViolationException, TodoCollectionException;
}
