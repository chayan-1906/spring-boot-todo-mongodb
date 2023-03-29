package com.padmanabhasmac.springbootmongodb.controllers;

import com.padmanabhasmac.springbootmongodb.models.TodoDTO;
import com.padmanabhasmac.springbootmongodb.repositories.ITodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class TodoController {

	@Autowired
	private ITodoRepository todoRepository;

	@GetMapping("/todos")
	public ResponseEntity<?> getAllTodos() {
		List<TodoDTO> todos = todoRepository.findAll ( );
		if (todos.size ( ) > 0) return new ResponseEntity<List<TodoDTO>> ( todos, HttpStatus.OK );
		else return new ResponseEntity<> ( "No Todos found", HttpStatus.NOT_FOUND );
	}

	@GetMapping("/todos/{todoId}")
	public ResponseEntity<?> getTodoById(@PathVariable String todoId) {
		try {
			TodoDTO todoDTO = todoRepository.findById ( todoId ).get ( );
			return new ResponseEntity<> ( todoDTO, HttpStatus.OK );
		} catch (Exception e) {
			return new ResponseEntity<> ( "Todo with ID " + todoId + " not found", HttpStatus.NOT_FOUND );
		}
	}

	@PostMapping("/todos")
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO todoDTO) {
		try {
			todoDTO.setCreatedAt ( new Date ( System.currentTimeMillis ( ) ) );
			todoRepository.save ( todoDTO );
			return new ResponseEntity<TodoDTO> ( todoDTO, HttpStatus.OK );
		} catch (Exception e) {
			return new ResponseEntity<> ( e.getMessage ( ), HttpStatus.INTERNAL_SERVER_ERROR );
		}
	}

	@PutMapping("/todos/{todoId}")
	public ResponseEntity<?> updateTodo(@RequestBody TodoDTO todoDTO, @PathVariable String todoId) {
		try {
			TodoDTO todoDTOToBeUpdated = todoRepository.findById ( todoId ).get ( );
			todoDTO.setCreatedAt ( todoDTOToBeUpdated.getCreatedAt ( ) );
			todoDTO.setUpdatedAt ( new Date ( System.currentTimeMillis ( ) ) );
			return new ResponseEntity<> ( todoRepository.save ( todoDTO ), HttpStatus.OK );
		} catch (Exception e) {
			return new ResponseEntity<> ( "Todo with ID " + todoId + " not found", HttpStatus.NOT_FOUND );
		}
	}

	@DeleteMapping("/todos/{todoId}")
	public ResponseEntity<?> deleteTodo(@PathVariable String todoId) {
		try {
			boolean todoDTO = todoRepository.findById ( todoId ).isPresent ( );
			if (todoDTO) {
				todoRepository.deleteById ( todoId );
				return new ResponseEntity<> ( "Todo with ID " + todoId + " deleted successfully", HttpStatus.OK );
			} else
				return new ResponseEntity<> ( "Todo with ID " + todoId + " not found", HttpStatus.NOT_FOUND );
		} catch (Exception e) {
			return new ResponseEntity<> ( "Todo with ID " + todoId + " not found", HttpStatus.NOT_FOUND );
		}
	}
}
