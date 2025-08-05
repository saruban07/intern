package com.social.demo.controller;

import com.social.demo.model.User;
import com.social.demo.service.FriendService;
import com.social.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000", "http://localhost:4173"})
public class UserController {

    private final UserService userService;
    private final FriendService friendService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return userService.login(user);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @PostMapping("/{senderId}/send/{receiverId}")
    public void sendFriendRequest(@PathVariable Long senderId, @PathVariable Long receiverId) {
        friendService.sendRequest(senderId, receiverId);
    }

    @PostMapping("/{receiverId}/accept/{senderId}")
    public void acceptFriendRequest(@PathVariable Long receiverId, @PathVariable Long senderId) {
        friendService.acceptRequest(receiverId, senderId);
    }
}
