//package com.example.filestorageapp.service;
//
//import com.example.filestorageapp.domain.UserOld;
//import com.example.filestorageapp.model.UserDTO;
//import com.example.filestorageapp.repository.UserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//@Service
//public class JwtUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    private PasswordEncoder bcryptEncoder;
//
//    public JwtUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////
////        UserOld user = userRepository.findByEmail(username);
////        if (user == null) {
////            throw new UsernameNotFoundException("User not found with username: " + username);
////        }
////
////        System.out.println("try to get user" + user.getId());
////
////        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
////                new ArrayList<>());
////    }
//
////    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
////        System.out.println("try to get user" + usernameOrEmail);
////
////        User user = userRepository.findByEmail("stdd01@gmail.com")
////                .orElseThrow(() ->
////                        new UsernameNotFoundException("User not found with username or email:" + usernameOrEmail));
////
////        System.out.println("user: " + user.getEmail() + user.getPassword());
////        return (UserDetails) user;
////    }
////
////    public UserOld save(UserDTO user) {
////        UserOld newUser = new UserOld();
////        newUser
////                .setUsername(user.getUsername())
////                .setEmail(user.getEmail())
////                .setPassword(bcryptEncoder.encode(user.getPassword()));
////
////        return userRepository.save(newUser);
////    }
//}
