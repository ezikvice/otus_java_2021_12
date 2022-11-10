package ru.dimk.service;

import ru.dimk.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> findAll();
    Optional<Client> findById(long id);
    Client findRandom();
    Client save(Client client);
}
