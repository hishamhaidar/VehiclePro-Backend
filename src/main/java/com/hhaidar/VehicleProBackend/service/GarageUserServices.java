package com.hhaidar.VehicleProBackend.service;

import com.hhaidar.VehicleProBackend.dto.AuthRequestDTO;
import com.hhaidar.VehicleProBackend.dto.AuthenticationResponseDTO;
import com.hhaidar.VehicleProBackend.dto.RegistrationRequestDTO;
import com.hhaidar.VehicleProBackend.dto.UserDataModificationRequestDTO;
import com.hhaidar.VehicleProBackend.exceptions.UserExists;
import org.springframework.http.ResponseEntity;

public interface GarageUserServices {
    ResponseEntity<String> registerUser(RegistrationRequestDTO registrationRequestDTO) throws UserExists;
    AuthenticationResponseDTO authenticateUser(AuthRequestDTO authRequest);
    String updateUser(Integer id, UserDataModificationRequestDTO request);
}
