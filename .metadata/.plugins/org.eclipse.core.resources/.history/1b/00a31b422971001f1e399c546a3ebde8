package com.example.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.movie.entity.User;
import com.example.movie.exception.InvalidCredentialsException;
import com.example.movie.exception.InvalidInputException;
import com.example.movie.exception.RequestDataException;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "APIs for managing Users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Get all users", description = "Retrieve a list of all users.")
    @GetMapping
    public List<User> getAllUsers()throws InvalidCredentialsException {
        try {
        	return userService.getAllUsers();
        }catch (Exception e) {
        	throw e;
        }
    }

    @Operation(summary = "Get user with given id", description = "Retrieve details of auser with given id.")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws InvalidCredentialsException{
       try {
    	   return ResponseEntity.ok(userService.getUserById(id));
       }catch (Exception e) {
    	   throw e;
       }
    }

    @Operation(summary = "Add a User", description = "Add a user")
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) throws InvalidCredentialsException, RequestDataException, InvalidInputException{
       try { 
    	   return ResponseEntity.ok(userService.registerUser(user));
       }catch (Exception e) {
    	   throw e;
       }
    }

    @Operation(summary = "Delete a User", description = "Delete a user with given Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws InvalidCredentialsException, ResourceNotFoundException {
        try { 
        	userService.deleteUser(id);
        return ResponseEntity.noContent().build();
        }catch (Exception e) {
        	throw e;
        }
    }
}
