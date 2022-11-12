package ru.dimk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.dimk.model.Client;
import ru.dimk.service.ClientService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientController {

    @Autowired
    ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        List<Client> clients = clientService.findAll();
        model.addAttribute("allClients", clients);
        return "index";
    }
}
