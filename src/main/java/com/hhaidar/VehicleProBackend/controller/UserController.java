package com.hhaidar.VehicleProBackend.controller;

import com.hhaidar.VehicleProBackend.dto.AuthRequest;
import com.hhaidar.VehicleProBackend.dto.AuthenticationResponse;
import com.hhaidar.VehicleProBackend.dto.RegistrationRequest;
import com.hhaidar.VehicleProBackend.exceptions.UserExists;
import com.hhaidar.VehicleProBackend.service.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserServices userServices;
    private  Logger log ;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(
            @RequestBody RegistrationRequest registrationRequest) throws UserExists {
        return ResponseEntity.ok(userServices.registerUser(registrationRequest));

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateUser(
            @RequestBody AuthRequest authRequestt) {
        return ResponseEntity.ok(userServices.authenticateUser(authRequestt));


    }
}
