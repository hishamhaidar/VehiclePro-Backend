package com.hhaidar.VehicleProBackend.repository;

import com.hhaidar.VehicleProBackend.model.GarageUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepo extends JpaRepository<GarageUser,Integer> {

    Optional<GarageUser> findUserByUserEmail(String userEmail);


    Optional<GarageUser> findUserByUsername(String username);
}
