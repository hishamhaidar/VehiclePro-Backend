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
public class User implements UserDetails {
    @Id
    @SequenceGenerator(name = "user_id_gen",
            sequenceName = "user_id_gen",
            allocationSize = 1)
    @GeneratedValue(strategy =GenerationType.SEQUENCE
            ,generator = "user_id_gen")
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
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    public User(String username, String userEmail, String userPassword) {
        this.username = username;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.role = Role.NEW_USER;

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
