package com.phuc.pcoreservice.controller;

import com.phuc.pcoreservice.payload.request.LoginRequest;
import com.phuc.pcoreservice.payload.request.SignupRequest;

import com.phuc.pcoreservice.payload.request.TokenRefreshRequest;
import com.phuc.pcoreservice.payload.response.TokenRefreshResponse;
import com.phuc.pcoreservice.service.IAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private IAuthService authService;


    @PostMapping(value = "signin")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);

    }

    @PostMapping(value = "signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest){
        return authService.register(signupRequest);
    }



}
