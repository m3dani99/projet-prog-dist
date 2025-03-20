package com.booking.api.service;

import com.booking.api.dto.ReservationRequest;
import com.booking.api.model.Client;
import com.booking.api.model.Reservation;
import com.booking.api.repository.ClientRepository;
import com.booking.api.repository.ReservationRepository;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;

    public ReservationService(ReservationRepository reservationRepository, ClientRepository clientRepository) {
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id){
        return reservationRepository.getReferenceById(id);
    }

    public Reservation createReservation(ReservationRequest request) throws NotFoundException {
        // Check if client exists
        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new NotFoundException());

        // Create and save reservation
        Reservation reservation = new Reservation();
        reservation.setClient(client);
        reservation.setRestaurantId(request.getRestaurantId());
        reservation.setReservationDate(request.getReservationDate());

        return reservationRepository.save(reservation);
    }
}
