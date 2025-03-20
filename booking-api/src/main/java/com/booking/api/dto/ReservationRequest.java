package com.booking.api.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationRequest {
    private Long clientId;
    private Long restaurantId;
    private LocalDateTime reservationDate;
}
