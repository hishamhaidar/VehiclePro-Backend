package com.hhaidar.VehicleProBackend.controller;

import com.hhaidar.VehicleProBackend.dto.AuthRequestDTO;
import com.hhaidar.VehicleProBackend.dto.AuthenticationResponseDTO;
import com.hhaidar.VehicleProBackend.dto.RegistrationRequestDTO;
import com.hhaidar.VehicleProBackend.dto.UserDataModificationRequestDTO;
import com.hhaidar.VehicleProBackend.exceptions.UserExists;
import com.hhaidar.VehicleProBackend.impl.GarageUserServicesImpl;
import com.hhaidar.VehicleProBackend.service.GarageUserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final GarageUserServices userServices;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestBody RegistrationRequestDTO registrationRequestDTO) throws UserExists {
        return userServices.registerUser(registrationRequestDTO);

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticateUser(
            @RequestBody AuthRequestDTO authRequestt) {
        return ResponseEntity.ok(userServices.authenticateUser(authRequestt));


    }
    @PutMapping("/role/{id}")
    public ResponseEntity<String> assignRole(@PathVariable Integer id , @RequestBody UserDataModificationRequestDTO req){
        return ResponseEntity.ok(userServices.updateUser(id,req));
    }
}
