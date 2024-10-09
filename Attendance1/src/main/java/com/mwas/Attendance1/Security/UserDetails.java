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
        }
        return null;
    }

    private String[] getroles(AllUsers userObj) {
        String roles=userObj.getRoles();
        if(roles==null || roles.isEmpty()){
            return new String[]{"ROLE_USER"};
        }
        return Arrays.stream(roles.split(","))
                .map(role->"ROLE_"+role.trim())
                .toArray(String[]::new);

    }
}
