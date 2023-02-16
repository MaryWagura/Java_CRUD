package com.example.demo.controllers;

import com.example.demo.db.UserRepository;
import com.example.demo.db.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> findAllUsers()
    {
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/{id}")

    public ResponseEntity<User>findUserById(@PathVariable (value="id") long id)
    {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
        {
            return ResponseEntity.ok().body(user.get());
        }else
        {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public User saveUser(@Validated @RequestBody User user)
    {

        return userRepository.save(user);

    }
    
    
}
