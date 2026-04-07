package com.var.userapi;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private List<User> users = new ArrayList<>();

    // CREATE
    @PostMapping
    public String createUser(@RequestBody User user) {
        users.add(user);
        return "User added";
    }

    // READ (all users)
    @GetMapping
    public List<User> getUsers() {
        return users;
    }

    // READ (by id)
    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // UPDATE
    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        for (User u : users) {
            if (u.getId() == id) {
                u.setName(updatedUser.getName());
                return "User updated";
            }
        }
        return "User not found";
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        users.removeIf(u -> u.getId() == id);
        return "User deleted";
    }
}
