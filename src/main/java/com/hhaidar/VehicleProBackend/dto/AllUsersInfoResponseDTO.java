package com.hhaidar.VehicleProBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllUsersInfoResponseDTO {
    public Integer userID;
    public String email;
    public String username;
    public String role;
}
