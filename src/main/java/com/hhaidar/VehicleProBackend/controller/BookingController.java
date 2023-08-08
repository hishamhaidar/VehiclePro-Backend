package com.hhaidar.VehicleProBackend.controller;

import com.hhaidar.VehicleProBackend.dto.BookingRequestDTO;
import com.hhaidar.VehicleProBackend.model.Booking;
import com.hhaidar.VehicleProBackend.impl.BookingServicesImpl;
import com.hhaidar.VehicleProBackend.service.BookingServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingServices bookingServices;

    @PostMapping("/book/{slotID}")
    public ResponseEntity<String> createBooking(@PathVariable Integer slotID, @RequestBody BookingRequestDTO bookingRequestDTO){
        return bookingServices.createBooking(slotID, bookingRequestDTO);
    }
    @CrossOrigin(
            origins = "http://localhost:3000",
            allowedHeaders = {"Authorization", "Content-Type"},
            allowCredentials = "true"
    )
    @PutMapping("/confirm/{bookingID}")
    public ResponseEntity<String> confirmBooking(@PathVariable Integer bookingID)
    {
        return bookingServices.confirmBooking(bookingID);
    }

    @PutMapping("/deny/{bookingID}")
    public ResponseEntity<String> denyBooking(@PathVariable Integer bookingID)
    {
        return bookingServices.denyBooking(bookingID);
    }

    @GetMapping("/viewall")
    public ResponseEntity<ArrayList<Booking>> viewAllBooking(){
        return bookingServices.viewAllBooking();
    }
    @GetMapping("/view/{slotID}")
    public ResponseEntity<ArrayList<Booking>> viewAllBookingBySlotID(@PathVariable Integer slotID){
        return bookingServices.viewAllBookingBySlotID(slotID);
    }
}
