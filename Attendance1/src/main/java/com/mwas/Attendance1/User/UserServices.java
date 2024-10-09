package com.mwas.Attendance1.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
@Autowired
private PasswordEncoder passwordEncoder;
@Autowired
private UserRepository userRepository;


    public AllUsers addUsers(AllUsers users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        userRepository.save(users);
        return users;
    }
}
