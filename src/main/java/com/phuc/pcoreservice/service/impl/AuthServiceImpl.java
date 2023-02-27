package com.phuc.pcoreservice.service.impl;


import com.phuc.pcoreservice.dto.ERole;
import com.phuc.pcoreservice.model.Role;
import com.phuc.pcoreservice.model.User;
import com.phuc.pcoreservice.payload.request.LoginRequest;
import com.phuc.pcoreservice.payload.request.SignupRequest;
import com.phuc.pcoreservice.payload.response.JwtResponse;
import com.phuc.pcoreservice.repository.IRoleRepo;
import com.phuc.pcoreservice.repository.IUserRepository;
import com.phuc.pcoreservice.security.jwt.JwtUtils;
import com.phuc.pcoreservice.security.services.UserDetailsImpl;
import com.phuc.pcoreservice.service.IAuthService;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements IAuthService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private IRoleRepo roleRepo;

    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt, null,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }


    @Override
    @Transactional
    public ResponseEntity<?> register(SignupRequest signUpRequest) {

        User userCheck = userRepository.findUserByUsername(signUpRequest.getUsername());
        if (userCheck.getId() != null) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));

        List<String> strRoles = signUpRequest.getRoles();

        List<Role> userRole = new ArrayList<>();
        if (strRoles != null) {
            strRoles.add(ERole.USER.name());
            userRole = roleRepo.findListByName(strRoles);
        } else {
            userRole = roleRepo.findListByName(strRoles);
        }

        Integer userId = userRepository.saveUser(user);
        roleRepo.saveRole(userRole, userId);
        return ResponseEntity.ok().body("Save role success!");
    }
}
