package com.hhaidar.VehicleProBackend.service;

import com.hhaidar.VehicleProBackend.dto.AuthRequestDTO;
import com.hhaidar.VehicleProBackend.dto.AuthenticationResponseDTO;
import com.hhaidar.VehicleProBackend.dto.RegistrationRequestDTO;
import com.hhaidar.VehicleProBackend.dto.UserDataModificationRequestDTO;
import com.hhaidar.VehicleProBackend.exceptions.UserExists;
import com.hhaidar.VehicleProBackend.model.User;
import com.hhaidar.VehicleProBackend.repository.UserRepo;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServices  {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponseDTO registerUser(RegistrationRequestDTO registrationRequestDTO) throws UserExists {
        boolean exists = userRepo.findUserByUserEmail(registrationRequestDTO.getEmail()).isPresent();
        if (exists){
            throw new UserExists("username is already taken,please provide new one");
        }
        User user = new User(registrationRequestDTO.getUsername(),
                registrationRequestDTO.getEmail()
                , passwordEncoder.encode(registrationRequestDTO.getPassword()));
        userRepo.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDTO.builder()
                .jwtToken(jwtToken)
                .build();


    }
    public AuthenticationResponseDTO authenticateUser(AuthRequestDTO authRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));
        Optional<User> user = userRepo.findUserByUserEmail(authRequest.getEmail());
        String jwtToken = jwtService.generateToken(user.get());
        return AuthenticationResponseDTO.builder()
                .jwtToken(jwtToken)
                .build();
    }

    public String updateUser(Integer id, UserDataModificationRequestDTO request){
        Optional<User> curr_user = userRepo.findById(id);
        if (!curr_user.isPresent() ) {
            throw new UsernameNotFoundException("User does not exist");
        }
        User user = new User(id,request.getUsername(),request.getEmail(),passwordEncoder.encode(request.getPassword()),request.getRole());
        userRepo.save(user);
        return user.toString()+" was saved";
    }


}
