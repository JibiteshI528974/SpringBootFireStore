package com.example.FireStoreSpringBoot.Controller;

import com.example.FireStoreSpringBoot.Component.User;
import com.example.FireStoreSpringBoot.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User addUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.addUser(user);
    }

    @GetMapping
    public List<User> getUsers() throws ExecutionException, InterruptedException {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) throws ExecutionException, InterruptedException {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public void updateUserById(@PathVariable String id, @RequestBody User user) throws ExecutionException, InterruptedException {
        userService.updateUserById(id, user);
    }
}
