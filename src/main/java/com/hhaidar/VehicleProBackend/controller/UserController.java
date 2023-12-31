package com.hhaidar.VehicleProBackend.controller;

import com.hhaidar.VehicleProBackend.dto.*;
import com.hhaidar.VehicleProBackend.exceptions.UserExists;
import com.hhaidar.VehicleProBackend.impl.GarageUserServicesImpl;
import com.hhaidar.VehicleProBackend.service.GarageUserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @GetMapping("/getinfo/{userEmail}")
    public ResponseEntity<UserInfoResponseDTO> getUserInfo(@PathVariable String userEmail){
        return userServices.getUserInfo(userEmail);
    }
    @GetMapping("/getall")
    public ResponseEntity<ArrayList<AllUsersInfoResponseDTO>> getUsers(){
        return userServices.getUsers();
    }
}
