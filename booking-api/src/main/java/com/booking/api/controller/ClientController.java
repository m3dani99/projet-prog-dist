package com.booking.api.controller;

import com.booking.api.model.Client;
import com.booking.api.service.ClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/clients")
@Tag(name = "Client API", description = "Operations related to clients")
public class ClientController {
    private final ClientService ClientService;

    public ClientController(ClientService ClientService) {
        this.ClientService = ClientService;
    }

    @Operation(summary = "Get all clients", description = "Retrieve a list of all clients")
    @GetMapping
    public List<Client> getClients() {
        return ClientService.getAllClients();
    }

    @Operation(summary = "Create a new client", description = "Add a new client to the system")
    @PostMapping
    public Client createClient(@RequestBody Client Client) {
        return ClientService.createClient(Client);
    }
}
