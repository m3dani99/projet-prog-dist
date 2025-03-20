package com.booking.api.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)  
    private Client client;

    @Column(name = "restaurant_id", nullable = false)  
    private Long restaurantId;

    @Column(name = "reservation_date", nullable = false)
    private LocalDateTime reservationDate;
}
