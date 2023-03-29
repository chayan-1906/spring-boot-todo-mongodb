package com.padmanabhasmac.springbootmongodb.repositories;

import com.padmanabhasmac.springbootmongodb.models.TodoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITodoRepository extends MongoRepository<TodoDTO, String> {
}
