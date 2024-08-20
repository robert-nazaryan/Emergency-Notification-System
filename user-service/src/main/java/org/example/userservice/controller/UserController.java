package org.example.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.userservice.model.User;
import org.example.userservice.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.createUser(user);
    }

}
