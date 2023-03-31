package com.padmanabhasmac.springbootmongodb.services;

import com.padmanabhasmac.springbootmongodb.exceptions.TodoCollectionException;
import com.padmanabhasmac.springbootmongodb.models.TodoDTO;
import com.padmanabhasmac.springbootmongodb.repositories.ITodoRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TodoServiceImpl implements ITodoService {

    @Autowired
    private ITodoRepository todoRepository;

    /**
     * @param todoDTO
     */
    @Override
    public void createTodo(TodoDTO todoDTO) throws ConstraintViolationException, TodoCollectionException {
        Optional<TodoDTO> todoDTOOptional = todoRepository.findByTodo(todoDTO.getTodo());
        if (todoDTOOptional.isPresent()) {
            throw new TodoCollectionException(TodoCollectionException.alreadyExistsException(todoDTO.getTodo()));
        } else {
            todoDTO.setCreatedAt(new Date(System.currentTimeMillis()));
            todoRepository.save(todoDTO);
        }
    }
}
