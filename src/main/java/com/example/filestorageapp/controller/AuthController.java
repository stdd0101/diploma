//package com.example.filestorageapp.controller;
//
//
//import com.example.filestorageapp.model.JwtRequest;
//import com.example.filestorageapp.model.JwtResponse;
//import com.example.filestorageapp.model.UserDTO;
//import com.example.filestorageapp.service.JwtTokenUtil;
//import com.example.filestorageapp.service.JwtUserDetailsService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin
//@RequestMapping(path = "/cloud")
//class AuthController {
//
//    private final AuthenticationManager authenticationManager;
//
//    private final JwtTokenUtil jwtTokenUtil;
//
//    private final JwtUserDetailsService userDetailsService;
//
//    public AuthController(AuthenticationManager authenticationManager,
//                          JwtTokenUtil jwtTokenUtil,
//                          JwtUserDetailsService userDetailsService) {
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenUtil = jwtTokenUtil;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) throws Exception {
//
//        this.authenticate(authenticationRequest.getLogin(), authenticationRequest.getPassword());
//
//        final UserDetails userDetails = userDetailsService
//                .loadUserByUsername(authenticationRequest.getLogin());
//
//        final String atoken = jwtTokenUtil.generateToken(userDetails);
//
//        System.out.println("try to get user" + atoken);
//
//        return ResponseEntity.ok(new JwtResponse(atoken));
//    }
//
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
//        return ResponseEntity.ok(userDetailsService.save(user));
//    }
//
//    private void authenticate(String username, String password) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (DisabledException exception) {
//            throw new Exception("USER_DISABLED", exception);
//        } catch (BadCredentialsException exception) {
//            throw new Exception("INVALID_CREDENTIALS", exception);
//        }
//
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(@RequestBody JwtRequest authenticationRequest) throws Exception {
//        return (ResponseEntity<?>) ResponseEntity.ok();
//    }
//
//}
