package ru.dimk.crm.controllers;

import org.springframework.web.bind.annotation.*;
import ru.dimk.crm.model.Client;
import ru.dimk.crm.services.ClientService;

import java.util.List;

//@RestController
public class ClientRestController {

    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/api/client/{id}")
    public Client getClientById(@PathVariable(name = "id") long id) {
        return clientService.findById(id).get();
    }

    @GetMapping("api/clients")
    public List<Client> getClients() {
        return clientService.findAll();
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
