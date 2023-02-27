package com.phuc.pcoreservice.service;

import com.phuc.pcoreservice.payload.request.LoginRequest;
import com.phuc.pcoreservice.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    ResponseEntity<?> login(LoginRequest loginRequest);

    ResponseEntity<?> register(SignupRequest signupRequest);
}
