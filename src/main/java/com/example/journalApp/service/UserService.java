package com.example.journalApp.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.example.journalApp.entity.User;
import com.example.journalApp.repositiory.UserRepository;

@Component
public class UserService{
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findyByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }
}
