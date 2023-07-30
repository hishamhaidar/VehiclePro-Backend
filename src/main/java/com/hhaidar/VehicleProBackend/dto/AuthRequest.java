package com.hhaidar.VehicleProBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthRequest {
    private final String email;
    private final String password;
}
