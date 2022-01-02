package com.example.examproject.controller;

import com.example.examproject.entity.User;
import com.example.examproject.payload.LoginDTO;
import com.example.examproject.repository.UserRepository;
import com.example.examproject.security.CurrentUser;
import com.example.examproject.security.JwtProvider;
import com.example.examproject.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/auth")
//@CrossOrigin
public class AuthController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthService authService;
    @Autowired
    AuthenticationManager authenticationManager;


//    @PostMapping("/register")


    @GetMapping("/me")
    public HttpEntity<?> getUser(@CurrentUser User user) {
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword()));

        User user = (User) authenticate.getPrincipal();

        String token = jwtProvider.generateToken(user.getUsername());
        return ResponseEntity.ok(token);
    }




}
