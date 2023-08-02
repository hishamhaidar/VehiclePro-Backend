package com.hhaidar.VehicleProBackend.dto;

import com.hhaidar.VehicleProBackend.model.Role;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDataModificationRequestDTO {
    private final String username;
    private final String email;
    private final String password;
    private final Role role;
}
