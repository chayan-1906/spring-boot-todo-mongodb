package com.padmanabhasmac.springbootmongodb.services;

import com.padmanabhasmac.springbootmongodb.exceptions.TodoCollectionException;
import com.padmanabhasmac.springbootmongodb.models.TodoDTO;
import com.padmanabhasmac.springbootmongodb.repositories.ITodoRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements ITodoService {

    @Autowired
    private ITodoRepository todoRepository;

    /**
     * @param todoDTO
     */
    @Override
    public TodoDTO createTodo(TodoDTO todoDTO) throws ConstraintViolationException, TodoCollectionException {
        Optional<TodoDTO> todoDTOOptional = todoRepository.findByTodo(todoDTO.getTodo());
        if (todoDTOOptional.isPresent()) {
            throw new TodoCollectionException(TodoCollectionException.alreadyExistsException(todoDTO.getTodo()));
        } else {
            todoDTO.setCreatedAt(new Date(System.currentTimeMillis()));
            todoRepository.save(todoDTO);
            return todoDTO;
        }
    }

    /**
     * @return
     */
    @Override
    public List<TodoDTO> getAllTodos() {
        List<TodoDTO> todos = todoRepository.findAll();
        if (todos.size() > 0) {
            return todos;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * @param todoID
     * @return
     * @throws TodoCollectionException
     */
    @Override
    public TodoDTO getTodo(String todoID) throws TodoCollectionException {
        Optional<TodoDTO> optionalTodoDTO = todoRepository.findById(todoID);
        if (!optionalTodoDTO.isPresent()) {
            throw new TodoCollectionException(TodoCollectionException.notFoundException(todoID));
        } else {
            return optionalTodoDTO.get();
        }
    }

    /**
     * @param todoID
     * @param todoDTO
     * @throws TodoCollectionException
     */
    @Override
    public TodoDTO updateTodo(String todoID, TodoDTO todoDTO) throws TodoCollectionException {
        Optional<TodoDTO> optionalTodoDTO = todoRepository.findById(todoID);
        Optional<TodoDTO> todoWithSameName = todoRepository.findByTodo(todoDTO.getTodo());
        if (optionalTodoDTO.isPresent()) {
            if (todoWithSameName.isPresent() && !todoWithSameName.get().getId().equals(todoID)) {
                System.out.println("updateTodo try: todo with id " + todoID + " already exists");
                throw new TodoCollectionException(TodoCollectionException.alreadyExistsException(todoID));
            }
            TodoDTO todoDTOToUpdate = optionalTodoDTO.get();
//            todoDTOToUpdate.setId(todoDTO.getId());
            todoDTOToUpdate.setTodo(todoDTO.getTodo());
            todoDTOToUpdate.setDescription(todoDTO.getDescription());
            todoDTOToUpdate.setCompleted(todoDTO.isCompleted());
            todoDTOToUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));
            todoRepository.save(todoDTOToUpdate);
            return todoDTOToUpdate;
        } else {
            System.out.println("updateTodo catch: todo with id " + todoID + " not found");
            throw new TodoCollectionException(TodoCollectionException.notFoundException(todoID));
        }
    }

    /**
     * @param todoID
     * @return
     * @throws TodoCollectionException
     */
    @Override
    public String deleteTodoById(String todoID) throws TodoCollectionException {
        Optional<TodoDTO> optionalTodoDTO = todoRepository.findById(todoID);
        if (optionalTodoDTO.isPresent()) {
            todoRepository.deleteById(todoID);
            return "Todo with id " + todoID + " deleted successfully";
        } else {
            throw new TodoCollectionException(TodoCollectionException.notFoundException(todoID));
        }
    }
}
