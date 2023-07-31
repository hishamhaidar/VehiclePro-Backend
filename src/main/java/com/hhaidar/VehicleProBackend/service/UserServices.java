package com.hhaidar.VehicleProBackend.service;

import com.hhaidar.VehicleProBackend.dto.AuthRequest;
import com.hhaidar.VehicleProBackend.dto.AuthenticationResponse;
import com.hhaidar.VehicleProBackend.dto.RegistrationRequest;
import com.hhaidar.VehicleProBackend.dto.UserDataModificationRequest;
import com.hhaidar.VehicleProBackend.exceptions.UserExists;
import com.hhaidar.VehicleProBackend.model.User;
import com.hhaidar.VehicleProBackend.repository.UserRepo;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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


    public AuthenticationResponse registerUser(RegistrationRequest registrationRequest) throws UserExists {
        boolean exists = userRepo.findUserByUserEmail(registrationRequest.getEmail()).isPresent();
        if (exists){
            throw new UserExists("username is already taken,please provide new one");
        }
        User user = new User(registrationRequest.getUsername(),
                registrationRequest.getEmail()
                , passwordEncoder.encode(registrationRequest.getPassword()));
        userRepo.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .jwtToken(jwtToken)
                .build();


    }
    public AuthenticationResponse authenticateUser(AuthRequest authRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));
        Optional<User> user = userRepo.findUserByUserEmail(authRequest.getEmail());
        String jwtToken = jwtService.generateToken(user.get());
        return AuthenticationResponse.builder()
                .jwtToken(jwtToken)
                .build();
    }

    public String updateUser(Integer id,UserDataModificationRequest request){
        Optional<User> curr_user = userRepo.findById(id);
        if (curr_user==null ) {
            throw new UsernameNotFoundException("User does not exist");
        }
        User user = new User(id,request.getUsername(),request.getEmail(),request.getPassword(),request.getRole());
        userRepo.save(user);
        return user.toString()+" was saved";
    }


}
