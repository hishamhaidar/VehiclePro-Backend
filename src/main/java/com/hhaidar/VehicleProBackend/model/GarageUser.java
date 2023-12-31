package com.hhaidar.VehicleProBackend.model;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "user_details")
public class GarageUser implements UserDetails {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Integer userID;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String userEmail;
    @Column(name = "password")
    private String userPassword;

    @Enumerated(EnumType.STRING)
    private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public String getUsername() {
        return this.userEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    public GarageUser(String username, String userEmail, String userPassword) {
        this.username = username;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.role = Role.NEW_USER;

    }
    public GarageUser(Integer id, String username, String userEmail, String userPassword, Role userRole) {
        this.userID=id;
        this.username = username;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.role = userRole;

    }
    public String getRealUserName(){
        return this.username;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
