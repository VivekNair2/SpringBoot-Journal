package com.example.journalApp.repositiory;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.journalApp.entity.User;

public interface UserRepository extends MongoRepository<User,ObjectId> {
    User findByUsername(String username); // Changed from findByUserName to match entity field name
}