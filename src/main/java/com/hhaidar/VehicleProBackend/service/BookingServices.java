package com.hhaidar.VehicleProBackend.service;

import com.hhaidar.VehicleProBackend.dto.BookingRequestDTO;
import com.hhaidar.VehicleProBackend.model.Booking;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public interface BookingServices {
    ResponseEntity<String> createBooking(Integer slotID, BookingRequestDTO bookingRequestDTO) ;
     ResponseEntity<String> confirmBooking(Integer bookingID);
     ResponseEntity<String> denyBooking(Integer bookingID);
     ResponseEntity<ArrayList<Booking>> viewAllBooking();
    ResponseEntity<ArrayList<Booking>> viewAllBookingBySlotID(Integer slotID);
}
