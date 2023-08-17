package com.hhaidar.VehicleProBackend.service;

import com.hhaidar.VehicleProBackend.dto.*;
import com.hhaidar.VehicleProBackend.exceptions.UserExists;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public interface GarageUserServices {
    ResponseEntity<String> registerUser(RegistrationRequestDTO registrationRequestDTO) throws UserExists;
    AuthenticationResponseDTO authenticateUser(AuthRequestDTO authRequest);
    String updateUser(Integer id, UserDataModificationRequestDTO request);

    ResponseEntity<UserInfoResponseDTO> getUserInfo(String userEmail);

    ResponseEntity<ArrayList<AllUsersInfoResponseDTO>> getUsers();
}
