package com.hhaidar.VehicleProBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponseDTO {
    private Integer userID;
    private String role;
    private String username;
}
