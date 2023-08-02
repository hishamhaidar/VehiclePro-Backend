package com.hhaidar.VehicleProBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthRequestDTO {
    private final String email;
    private final String password;
}
