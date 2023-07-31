package com.hhaidar.VehicleProBackend.repository;

import com.hhaidar.VehicleProBackend.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Integer> {
    ArrayList<Booking> findAllBySlotID(Integer slotID);

}
