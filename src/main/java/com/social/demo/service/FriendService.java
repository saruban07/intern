package com.social.demo.service;

import com.social.demo.model.User;
import com.social.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final UserRepository userRepository;

    public void sendRequest(Long senderId, Long receiverId) {
        User sender = userRepository.findById(senderId).orElse(null);
        User receiver = userRepository.findById(receiverId).orElse(null);

        if (sender != null && receiver != null) {
            sender.getSentRequests().add(receiver);
            userRepository.save(sender);
        }
    }

    public void acceptRequest(Long receiverId, Long senderId) {
        User receiver = userRepository.findById(receiverId).orElse(null);
        User sender = userRepository.findById(senderId).orElse(null);

        if (receiver != null && sender != null && sender.getSentRequests().contains(receiver)) {
            receiver.getFriends().add(sender);
            sender.getFriends().add(receiver);

            sender.getSentRequests().remove(receiver);

            userRepository.save(sender);
            userRepository.save(receiver);
        }
    }
}
