package com.booking.api.service;

import com.booking.api.model.Client;
import com.booking.api.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository ClientRepository;

    public ClientService(ClientRepository ClientRepository) {
        this.ClientRepository = ClientRepository;
    }

    public List<Client> getAllClients() {
        return ClientRepository.findAll();
    }

    public Client createClient(Client Client) {
        return ClientRepository.save(Client);
    }
}
