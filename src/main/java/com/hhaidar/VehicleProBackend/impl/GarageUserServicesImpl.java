package com.hhaidar.VehicleProBackend.impl;

import com.hhaidar.VehicleProBackend.config.JWTService;
import com.hhaidar.VehicleProBackend.dto.*;
import com.hhaidar.VehicleProBackend.exceptions.UserExists;
import com.hhaidar.VehicleProBackend.model.GarageUser;
import com.hhaidar.VehicleProBackend.repository.UserRepo;
import com.hhaidar.VehicleProBackend.service.GarageUserServices;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GarageUserServicesImpl implements GarageUserServices {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<String> registerUser(RegistrationRequestDTO registrationRequestDTO) throws UserExists {
        boolean mailExists = userRepo.findUserByUserEmail(registrationRequestDTO.getEmail()).isPresent();
        if (mailExists){
            return ResponseEntity.badRequest().body("Email already taken");
        }
         boolean usernameExists = userRepo.findUserByUsername(registrationRequestDTO.getUsername()).isPresent();
        if (usernameExists){
            return ResponseEntity.badRequest().body("Username already taken");
        }
        GarageUser garageUser = new GarageUser(registrationRequestDTO.getUsername(),
                registrationRequestDTO.getEmail()
                , passwordEncoder.encode(registrationRequestDTO.getPassword()));
        userRepo.save(garageUser);

        return ResponseEntity.ok("User Registered succesfully");


    }
    @Override
    public AuthenticationResponseDTO authenticateUser(AuthRequestDTO authRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));
        Optional<GarageUser> user = userRepo.findUserByUserEmail(authRequest.getEmail());
        String jwtToken = jwtService.generateToken(user.get());
        return AuthenticationResponseDTO.builder()
                .jwtToken(jwtToken)
                .build();
    }
    @Override
    public String updateUser(Integer id, UserDataModificationRequestDTO request){
        Optional<GarageUser> curr_user = userRepo.findById(id);
        if (!curr_user.isPresent() ) {
            throw new UsernameNotFoundException("GarageUser does not exist");
        }
        GarageUser garageUser = new GarageUser(id,request.getUsername(),request.getEmail(),passwordEncoder.encode(curr_user.get().getPassword()),request.getRole());
        userRepo.save(garageUser);
        return garageUser.toString()+" was saved";
    }

    @Override
    public ResponseEntity<UserInfoResponseDTO> getUserInfo(String userEmail) {
        Optional<GarageUser> curr_user = userRepo.findUserByUserEmail(userEmail);
        if (!curr_user.isPresent() ) {
            return ResponseEntity.badRequest().body(new UserInfoResponseDTO());
        }
        GarageUser user= curr_user.get();
        UserInfoResponseDTO response = new UserInfoResponseDTO(user.getUserID(),user.getRole().name(),user.getRealUserName());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ArrayList<AllUsersInfoResponseDTO>> getUsers() {
        ArrayList<GarageUser> allUsers = (ArrayList<GarageUser>)userRepo.findAll();
        ArrayList<AllUsersInfoResponseDTO> response = new ArrayList<>();
    allUsers.forEach(user ->{
        AllUsersInfoResponseDTO info = AllUsersInfoResponseDTO.builder()
                .userID(user.getUserID())
                .email(user.getUserEmail())
                .username(user.getRealUserName())
                .role(user.getRole().name()).build();
        response.add(info);
    });
    return ResponseEntity.ok(response);
    }


}
