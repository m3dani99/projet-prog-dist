package com.booking.api.controller;

import com.booking.api.client.RestaurantServiceClient;
import com.booking.api.dto.ReservationRequest;
import com.booking.api.dto.ReservationResponse;
import com.booking.api.dto.RestaurantDTO;
import com.booking.api.model.Reservation;
import com.booking.api.service.ReservationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservations")
@Tag(name = "Reservation API", description = "Operations related to reservations")
public class ReservationController {
    private final ReservationService reservationService;
    private final RestaurantServiceClient restaurantServiceClient;

    public ReservationController(ReservationService reservationService, RestaurantServiceClient restaurantServiceClient) {
        this.reservationService = reservationService;
        this.restaurantServiceClient = restaurantServiceClient;
    }

    @Operation(summary = "Get all reservations", description = "Retrieve a list of all reservations")
    @GetMapping
    public List<ReservationResponse> getReservations() {
        
        List<Reservation> reservations = reservationService.getAllReservations();
        return reservations.stream().map(reservation -> {
            RestaurantDTO restaurant = restaurantServiceClient.getRestaurantById(reservation.getRestaurantId());
            return new ReservationResponse(reservation, restaurant);
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ReservationResponse getReservation(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        RestaurantDTO restaurant = restaurantServiceClient.getRestaurantById(reservation.getRestaurantId());
        return new ReservationResponse(reservation, restaurant);
    }

    @Operation(summary = "Create a new reservation", description = "Add a new reservation to the system")
    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationRequest request) throws NotFoundException {
        Reservation reservation = reservationService.createReservation(request);
        RestaurantDTO restaurant = restaurantServiceClient.getRestaurantById(reservation.getRestaurantId());
        ReservationResponse response = new ReservationResponse(reservation, restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
