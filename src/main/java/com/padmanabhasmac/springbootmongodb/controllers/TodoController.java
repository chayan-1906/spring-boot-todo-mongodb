package com.padmanabhasmac.springbootmongodb.controllers;

import com.padmanabhasmac.springbootmongodb.exceptions.TodoCollectionException;
import com.padmanabhasmac.springbootmongodb.models.TodoDTO;
import com.padmanabhasmac.springbootmongodb.repositories.ITodoRepository;
import com.padmanabhasmac.springbootmongodb.services.ITodoService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private ITodoRepository todoRepository;

    @Autowired
    private ITodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos() {
        List<TodoDTO> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos, todos.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/todos/{todoID}")
    public ResponseEntity<?> getTodoById(@PathVariable String todoID) {
        try {
            return new ResponseEntity<>(todoService.getTodo(todoID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Todo with ID " + todoID + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todoDTO) {
        try {
            TodoDTO createdTodo = todoService.createTodo(todoDTO);
            return new ResponseEntity<>(createdTodo, HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (TodoCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/todos/{todoID}")
    public ResponseEntity<?> updateTodo(@RequestBody TodoDTO todoDTO, @PathVariable String todoID) {
        try {
            TodoDTO updatedTodo = todoService.updateTodo(todoID, todoDTO);
            return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (TodoCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/todos/{todoID}")
    public ResponseEntity<?> deleteTodo(@PathVariable String todoID) {
        try {
            todoService.deleteTodoById(todoID);
            return new ResponseEntity<>("Todo with id " + todoID + " deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Todo with ID " + todoID + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
