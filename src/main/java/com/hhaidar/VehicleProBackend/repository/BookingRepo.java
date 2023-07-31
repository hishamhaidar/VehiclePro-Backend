package com.hhaidar.VehicleProBackend.repository;

import com.hhaidar.VehicleProBackend.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Integer> {
    ArrayList<Booking> findAllBySlotID(Integer slotID);
    Optional<Booking> findBySlotIDAndClientEmail(Integer slotID,String clientEmail);
}
