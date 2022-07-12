package com.revature.controllers;

import com.revature.exception.InvalidCredentialException;
import com.revature.exception.InvalidUserException;
import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private UserService us;

    @Autowired
    public UserController(UserService us){
        this.us = us;
    }

    @PostMapping("/user/")
    public ResponseEntity<Object> handleRegisterUser(@RequestBody LinkedHashMap<String, String> body){

        try {
            User u = us.registerNewUser(body.get("firstName"), body.get("lastName"), body.get("username"), body.get("email"), body.get("password"));
            System.out.println(u);
            return new ResponseEntity<>(u, HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>("invalid username or email", HttpStatus.CONFLICT);
        }

    }

    @PostMapping("/user/login")
    public ResponseEntity<Object> handleUserLogin(@RequestBody LinkedHashMap<String, String> body){

        String username = body.get("username");
        String password = body.get("password");

        try {
            return new ResponseEntity<>(us.loginUser(username, password), HttpStatus.ACCEPTED);
        } catch (InvalidCredentialException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }

    }

    @PutMapping("/user/follow")
    public User handleFollowUser(@RequestParam(name = "user")int user, @RequestParam(name = "toFollow")int toFollow ){
        return us.followUser(user, toFollow);
    }

    @GetMapping("/user/followers/{id}")
    public Set<User> handleUserFollowers(@PathVariable("id")int id){
        return us.getCurrentUserById(id).getFollowers();
    }

    @GetMapping("/user/following/{id}")
    public Set<User> handleUserFollowing(@PathVariable("id")int id){
        return us.getCurrentUserById(id).getFollowing();
    }

    @GetMapping("/user")
    public User getCurrentUser(@RequestParam(name="id")int id){
        return us.getCurrentUserById(id);
    }
}
