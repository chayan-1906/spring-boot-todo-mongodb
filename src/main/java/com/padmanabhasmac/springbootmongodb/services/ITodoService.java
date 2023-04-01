package com.padmanabhasmac.springbootmongodb.services;

import com.padmanabhasmac.springbootmongodb.exceptions.TodoCollectionException;
import com.padmanabhasmac.springbootmongodb.models.TodoDTO;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

public interface ITodoService {

    TodoDTO createTodo(TodoDTO todoDTO) throws ConstraintViolationException, TodoCollectionException;

    List<TodoDTO> getAllTodos();

    TodoDTO getTodo(String todoID) throws TodoCollectionException;

    TodoDTO updateTodo(String todoID, TodoDTO todoDTO) throws TodoCollectionException;

    String deleteTodoById(String todoID) throws TodoCollectionException;
}
