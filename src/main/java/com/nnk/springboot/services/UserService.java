package com.nnk.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
    }

    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public User update(Integer id, User user) {
        user.setId(id);

        user.setPassword(encoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public void delete(Integer id) {
        User user = findById(id);
        userRepository.delete(user);
    }

}