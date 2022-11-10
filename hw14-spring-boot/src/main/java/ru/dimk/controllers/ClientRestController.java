package ru.dimk.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dimk.model.Client;
import ru.dimk.service.ClientService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class ClientRestController {

    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/api/clients/{id}")
    public Client getClientById(@PathVariable(name = "id") long id) {
        return clientService.findById(id).get();
    }

    @GetMapping("api/clients")
    public List<Client> getClients() {
        return clientService.findAll();
    }

    @PostMapping(value = "/api/clients", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> createClient(@RequestBody Client client, HttpServletResponse response) {
        try {
            clientService.save(client);
            return new ResponseEntity<>("Client was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/client")
    public Client saveClient(@RequestBody Client client) {
        return clientService.save(client);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/client/random")
    public Client findRandomClient() {
        return clientService.findRandom();
    }

}
