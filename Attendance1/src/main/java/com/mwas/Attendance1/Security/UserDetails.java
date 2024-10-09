package com.mwas.Attendance1.Security;

import com.mwas.Attendance1.User.AllUsers;
import com.mwas.Attendance1.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
@Service
public class UserDetails implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional <AllUsers> users=userRepository.findByUsername(username);
        if(users.isPresent()){
            var userObj=users.get();
            return User
                    .builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(getroles(userObj))
                    .build();
        }else {
           throw new UsernameNotFoundException("not found");
        }
    }

    private String[] getroles(AllUsers userObj) {

        if(userObj.getRoles()==null){
            return new String[]{"USER"};
        }
        return userObj.getRoles().split(",");

    }
}
