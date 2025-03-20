package com.booking.api.dto;

import com.booking.api.model.Reservation;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponse {
    private Long id;
    private Long clientId;
    private RestaurantDTO restaurant;
    private String reservationDate;

    public ReservationResponse(Reservation reservation, RestaurantDTO restaurant) {
        this.id = reservation.getId();
        this.clientId = reservation.getClient().getId();
        this.restaurant = restaurant;
        this.reservationDate = reservation.getReservationDate().toString();
    }
}

