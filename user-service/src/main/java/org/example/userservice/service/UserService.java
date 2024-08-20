package org.example.userservice.service;

import lombok.RequiredArgsConstructor;
import org.example.userservice.model.User;
import org.example.userservice.repository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }

}
