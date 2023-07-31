package com.hhaidar.VehicleProBackend.repository;

import com.hhaidar.VehicleProBackend.model.ServiceSlots;
import com.hhaidar.VehicleProBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findUserByUserEmail(String userEmail);


}
