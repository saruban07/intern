package com.social.demo.service;

import com.social.demo.model.User;
import com.social.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User register(User user) {
        // Check if user already exists
        User existingUser = userRepository.findByUsername(user.getUsername()).orElse(null);
        if (existingUser != null) {
            throw new RuntimeException("User already exists with this username");
        }
        return userRepository.save(user);
    }

    public User login(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername()).orElse(null);
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return existingUser;
        }
        throw new RuntimeException("Invalid username or password");
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
